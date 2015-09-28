package ru.bugrimov.model;

public class Decoder {
    private static final int FRACTIONAL_PART = 20;
    public final String SYMBOL = "0123456789ABCDEF";

    private int value(final String str) {
        return SYMBOL.indexOf(str.toUpperCase());
    }

    private double step(long n, int st) {
        double result = 1;
        if (st == 0) {
            return 1;
        } else {
            for(int i = 1; i <= Math.abs(st); i++) {
                result = result * n;
            }
            if (st < 0) {
                result = 1.0 / result;
            }
        }
        return result;
    }

    public String conversionOfNumber(String number, long from, long to) {
        String integerPart;     // Целая часть
        String fractionalPart;  // Дробная часть
        double sum;
        String result = "";

        if (number.indexOf(".") > 0) {
            integerPart = number.substring(0, number.indexOf("."));
            fractionalPart = number.substring(number.indexOf(".") + 1);
        } else {
            integerPart = number;
            fractionalPart = "";
        }

        if ((from < 2 || from > 16) || (to < 2 || to > 16)) {
            return "Ошибка при вводе системы счисления!";
        }

        if (from == to) {
            return number;
        } else {
            if (to == 10) {
                sum = value(integerPart.substring(0, 1)) * step(from, (integerPart.length()-1));
                for (int i = integerPart.length()-2; i >= 0; i--) {
                    sum += (value(integerPart.substring(integerPart.length()-i-1, integerPart.length()-i)) * step(from, i));
                }
                for (int i = 0; i <= fractionalPart.length()-1; i++) {
                    sum += value(fractionalPart.substring(i, i+1)) * step(from, -i-1);
                }
                return "" + sum;
            }
            if (from == 10) {
                long valueInt = Long.parseLong(integerPart),ost = 0;
                number = "";
                if (valueInt != 0) {
                    do{
                        result = SYMBOL.charAt((int)(valueInt - (valueInt / to) * to)) + result;
                        valueInt = (valueInt / to);
                    } while (valueInt > 0);
                    result = result + "";
                } else {
                    result = "0.";
                }
                if (fractionalPart.length() > 0) {
                    double valueDouble = Double.parseDouble("0." + fractionalPart);
                    for (int i = 1; i <= FRACTIONAL_PART; i++) {
                        result = result + SYMBOL.charAt((int)(valueDouble * to));
                        valueDouble = (valueDouble * to) - (int)(valueDouble * to);
                        if (valueDouble == 0) {
                            i = FRACTIONAL_PART;
                        }
                    }
                }
                return result;
            }
            else {
                return conversionOfNumber(conversionOfNumber(number, from, 10), 10, to);
            }
        }
    }
}
