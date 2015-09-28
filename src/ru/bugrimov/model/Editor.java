package ru.bugrimov.model;

public class Editor {
    private static final String SEPARATOR = ".";
    private String number;
    private long ccFrom;
    private long ccTo;
    public History history = new History();;

    public History getHistory() {
        return history;
    }

    public long getCcFrom() {
        return ccFrom;
    }

    public void setCcFrom(long ccFrom) {
        this.ccFrom = ccFrom;
    }

    public long getCcTo() {
        return ccTo;
    }

    public void setCcTo(long ccTo) {
        this.ccTo = ccTo;
    }

    public Editor(final String number, final long ccFrom, final long ccTo) {
        this.number = number;
        this.ccFrom = ccFrom;
        this.ccTo = ccTo;
    }

    private String getNumber() {
        return number;
    }

    private void setNumber(String number) {
        this.number = number;
    }

    /** Добавить знак */
    public String addSign() {
        try {
            int number = ((Integer.parseInt(this.getNumber())) * (-1));
            String current = String.valueOf(number);
            this.setNumber(current);
            return current;
        } catch (NumberFormatException e) {
            String str = this.getNumber();
            if (str.charAt(0) != '-') {
                str = "-" + str;
            } else {
                str = str.substring(1);
            }
            this.setNumber(str);
            return str;
        }
    }

    /** Добавить разделитель */
    public String addSeparator() {
        String currentString = this.getNumber();
        if (!currentString.contains(".") || !currentString.contains(",") && !currentString.startsWith("0.")) {
            currentString += SEPARATOR;
        } else {
            currentString = "";
        }
        this.setNumber(currentString);
        return currentString;
    }

    /** Добавить число */
    public String addNumber(final String number) {
        String currentString = this.getNumber();
        String newString;
        if (!currentString.equals("0") || currentString.startsWith("0.")) {
            newString = currentString + number;

        } else {
            newString = number;
        }
        this.setNumber(newString);
        return newString;
    }

    /** Добавить 0 */
    public String addZero() {
        String currentString = this.getNumber();
        if (currentString.startsWith("0.") || !currentString.equals("0")) {
            currentString += "0";
        } else {
            currentString = "0";
        }
        this.setNumber(currentString);
        return currentString;
    }

    /** Удаление символа справа */
    public String backspace() {
        String currentString = getNumber();
        String newString = "";
        if ((currentString.length() > 1) && currentString.charAt(currentString.length() - 2) != '-') {
            int ch = (currentString.charAt(currentString.length() - 2) == '.') ? 2 : 1;
            newString =  currentString.substring(0, (currentString.length() - ch));
        } else {
            newString = "0";
        }
        this.setNumber(newString);
        return newString;
    }

    /** Редактировать */
    public String edit() {
        Decoder decoder = new Decoder();

        String current = this.getNumber();
        String result;
        if (current.charAt(0) != '-') {
            result = decoder.conversionOfNumber(current, this.getCcFrom(), this.getCcTo());
        } else {
            result = "-" + decoder.conversionOfNumber(current.substring(1), this.getCcFrom(), this.getCcTo());
        }
        history.write(((history.size())+1) + ". Исходное (" + this.getCcFrom() + "): " + current + " -> Результат (" + this.getCcTo() + "): "+ result);
        return result;
    }

    public String clear() {
        this.setNumber("0");
        return "0";
    }

    public boolean findSeparator() {
        String string = this.getNumber();
        char[] ch = string.toCharArray();
        for (char aCh : ch) {
            if (aCh == '.' || aCh == ',' || aCh == '/') {
                return true;
            }
        }
        return false;
    }
}
