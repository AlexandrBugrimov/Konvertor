package ru.bugrimov.model;


public class NumberBaseConverter {
    public static void validateBase(int base) {
        if (base < 2 || base > 36) {
            System.out.println("Нет такой системы счисления");
            System.exit(1);
        }
    }

    public static void validateNumInBase(String num, int base) {
        char chDigit;
        for (int i = 0; i < num.length(); i++) {
            chDigit = num.toUpperCase().charAt(i);
            if (Character.isDigit(chDigit) && (chDigit - '0') >= base) {
                System.out.println("Не могу преобразовать " + chDigit + " в систему " + base);
                System.exit(1);
            } else if (Character.isLetter(chDigit) && (chDigit - 'A') + 10 >= base) {
                System.out.println("Не могу преобразовать " + chDigit + " в систему " + base);
                System.exit(1);
            } else if (!Character.isDigit(chDigit) && !Character.isLetter(chDigit)) {
                System.out.println("Не могу преобразовать " + chDigit + " в систему " + base);
                System.exit(1);
            }
        }
    }


    public static String numConvertBase(String origNum, int origBase, int newBase) {
        double val = 0;
        double decDigit = 0;
        char chDigit;
        int L = origNum.length();

        for (int p = 0; p < L; p++) {
            chDigit = Character.toUpperCase(origNum.charAt(L-1-p));
            if (Character.isLetter(chDigit)) {
                decDigit = chDigit - 'A' + 10;
            } else if (Character.isDigit(chDigit)) {
                decDigit = chDigit - '0';
            } else {
                System.out.println("Error");
                //System.exit(1);
            }
            val += decDigit * Math.pow(origBase, p);
        }

        int D = 1;
        for (; Math.pow(newBase, D) <= val; D++) {}

        char[] newNum = new char[D];
        double pwr;

        for (int p = D-1; p >= 0 ; p--) {
            pwr = Math.pow(newBase, p);
            decDigit = Math.floor(val / pwr);
            val -= decDigit * pwr;

            if (decDigit <= 9) {
                newNum[D-1-p] = (char)('0' + (int)decDigit);
            } else {
                newNum[D-1-p] = (char)('A' + (int)(decDigit-10));
            }
        }

        return new String(newNum);
    }
}
