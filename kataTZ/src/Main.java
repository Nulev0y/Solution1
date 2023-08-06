import java.util.Scanner;

public class Main {
    private static int[] intervals = {1, 4, 5, 9, 10, 40, 50, 90, 100};
    private static String[] numerals = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input: ");
        String input = scanner.nextLine();

        try {
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException(" Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

            String a = parts[0];
            String operator = parts[1];
            String b = parts[2];

            boolean isArabic = isArabicNumber(a) && isArabicNumber(b);
            boolean isRoman = isRomanNumber(a) && isRomanNumber(b);

            if (isArabic && !isRoman) {
                int result = calculateArabic(Integer.parseInt(a), operator, Integer.parseInt(b));
                System.out.println("Результат: " + result);
            } else if (isRoman && !isArabic) {
                int firstArabic = romanToArabic(a);
                int secondArabic = romanToArabic(b);
                int result = calculateArabic(firstArabic, operator, secondArabic);
                String resultRoman = arabicToRoman(result);
                System.out.println("Результат: " + resultRoman);
            } else {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isArabicNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isRomanNumber(String input) {
        return input.matches("^(?i)M*(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$");
    }

    private static int romanToArabic(String roman) {
        int result = 0;
        int i = intervals.length - 1;
        int index = 0;
        while (i >= 0 && roman.length() > 0) {
            String symbol = numerals[i];
            while (roman.indexOf(symbol) == 0 && roman.length() > 0) {
                result += intervals[i];
                roman = roman.substring(symbol.length());
            }
            i--;
            index++;
        }
        return result;
    }
    private static boolean isSubtractionInvalid(int firstArabic, int secondArabic) {
        return firstArabic < secondArabic;
    }

    private static String arabicToRoman(int number) {
        StringBuilder roman = new StringBuilder();
        int i = intervals.length - 1;
        while (number > 0) {
            if (number >= intervals[i]) {
                roman.append(numerals[i]);
                number -= intervals[i];
            } else {
                i--;
            }
        }
        return roman.toString();
    }

    private static int calculateArabic(int a, String operator, int b) {
        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                if (isRomanNumber(operator) && isSubtractionInvalid(a, b)) {
                    throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
                }
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Деление на ноль недопустимо");
                }
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Строка не является математической операцией");
        }
        return result;
    }
}
