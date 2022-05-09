import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static String textA;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        String newText = inputText.replace(" ","");
        if (newText.contains(".")) {
            throw new IOException();
        } else {
            int newTextLength = newText.length();
            int indexOperation = indexOperation(newText);
            String textA = newText.substring(0, indexOperation);
            String textB = newText.substring(indexOperation + 1, newTextLength);
            int A = getArabicFromText(textA);
            int B = getArabicFromText(textB);
            int result = getOperation(newText.charAt(indexOperation), A, B);
            boolean arabicAndRoman = getTestHaveArabicAndRoman(textA, textB);
            boolean allRoman = getTestAllRoman(textA, textB);
            boolean allArabic = getTestAllArabic(textA, textB);

            if (arabicAndRoman){
                throw new IOException();
            } else if (allRoman){
                if (result < 0) {
                    throw new IOException();
                } else {
                    RomanToArabic romanNum[] = RomanToArabic.values();
                    String resultRoman = String.valueOf(romanNum[result]);
                    System.out.println("Результат с римскими цифрами = " + resultRoman);
                }
            } else if (allArabic){
                System.out.println("Результат с арабскими цифрами = " + result);
            }
        }
    }

    private static boolean getTestAllArabic(String textA, String textB) {
        boolean testAllArabic = false;
        if ((textB.contains("1") || textB.contains("2") || textB.contains("3")  ||textB.contains("4") || textB.contains("5") ||
                textB.contains("6") || textB.contains("7") || textB.contains("8") || textB.contains("9")) &&
                (textA.contains("1") || textA.contains("2") || textA.contains("3")  ||textA.contains("4") || textA.contains("5") ||
                        textA.contains("6") || textA.contains("7") || textA.contains("8") || textA.contains("9"))) {
            testAllArabic = true;
        }
        return testAllArabic;
    }
    private static boolean getTestAllRoman(String textA, String textB) {
        boolean testAllRoman = false;
        if ((textA.contains("I") || textA.contains("V") || textA.contains("X")) && (textB.contains("I") || textB.contains("V") || textB.contains("X"))) {
            testAllRoman = true;
        }
        return testAllRoman;
    }
    private static boolean getTestHaveArabicAndRoman(String textA,String textB) {
        boolean testArabicAndRoman;
        if ((textA.contains("I") || textA.contains("V") || textA.contains("X")) &&
                (textB.contains("1") || textB.contains("2") || textB.contains("3")  ||textB.contains("4") || textB.contains("5") ||
                        textB.contains("6") || textB.contains("7") || textB.contains("8") || textB.contains("9"))) {
            testArabicAndRoman = true;
        } else if ((textB.contains("I") || textB.contains("V") || textB.contains("X")) &&
                (textA.contains("1") || textA.contains("2") || textA.contains("3")  ||textA.contains("4") || textA.contains("5") ||
                        textA.contains("6") || textA.contains("7") || textA.contains("8") || textA.contains("9"))) {
            testArabicAndRoman = true;
        } else testArabicAndRoman = false;
        return testArabicAndRoman;
    }
    private static int getArabicFromText(String inputText) throws IOException {
        int i = switch (inputText) {
            case "0" -> 0;
            case "1", "I" -> 1;
            case "2", "II" -> 2;
            case "3", "III" -> 3;
            case "4", "IV" -> 4;
            case "5", "V" -> 5;
            case "6", "VI" -> 6;
            case "7", "VII" -> 7;
            case "8", "VIII" -> 8;
            case "9", "IX" -> 9;
            case "10", "X" -> 10;
            default -> throw new IOException();
        };
        return i;
    }
    private static int indexOperation(String newText) {
        int iOp = newText.indexOf('+');
        if (iOp == -1) {
            iOp = newText.indexOf('-');
            if (iOp == -1) {
                iOp = newText.indexOf('*');
                if (iOp == -1) {
                    iOp = newText.indexOf('/');
                }
            }
        }
        return iOp;
    }
    private static int getOperation(char sOp, int a, int b) {
        int result = 0;
        switch (sOp) {
            case '+' -> result = a + b;
            case '-' -> result = a - b;
            case '/' -> result = a / b;
            case '*' -> result = a * b;
        }
        return result;
    }
}
