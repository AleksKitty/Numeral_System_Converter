package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean smthWentWrong;
        try {
            int radixOfNum = Integer.parseInt(scanner.next());

            String numInString = scanner.next();

            int radixTarget = Integer.parseInt(scanner.next());

            String integerPartInString = numInString.split("\\.")[0];

            smthWentWrong = checkAnyPart(radixOfNum, integerPartInString, radixTarget); // check!

            if (!smthWentWrong) {
                integerPart(radixOfNum, integerPartInString, radixTarget);

                if (numInString.matches(".*\\..*")) {
                    String fractionalPartInString = numInString.split("\\.")[1];

                    smthWentWrong = checkAnyPart(radixOfNum, fractionalPartInString, radixTarget); // check!

                    if (!smthWentWrong) {
                        fractionalPart(radixOfNum, fractionalPartInString, radixTarget);
                    } else {
                        System.out.println("Error");
                    }
                }
            } else {
                System.out.println("Error");
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void integerPart(int radixOfNum, String integerPartInString, int radixTarget) {
        long num;
        if (radixOfNum == 1) {
            num = integerPartInString.length();
        } else {
            num = Long.parseLong(integerPartInString, radixOfNum);
        }

        if (radixTarget == 1) {
            for (int i = 0; i < num; i++) {
                System.out.print(1);
            }
        } else {
            String numTarget = Long.toString(num, radixTarget);

            System.out.print(numTarget);
        }
    }

    public static void fractionalPart(int radixOfNum, String fractionalPartInString, int radixTarget) {
        double decimalNumber = 0;

        if (radixTarget != 1 && radixOfNum != 1) {
            for (int i = 1; i <= fractionalPartInString.length(); i++) {
                decimalNumber += Long.parseLong(String.valueOf(fractionalPartInString.charAt(i - 1)), radixOfNum) / Math.pow(radixOfNum, i);
            }

            while (decimalNumber > 1) {
                decimalNumber /= 10;
            }

            int integralPart;

            StringBuilder numTarget = new StringBuilder();
            for (int i = 5; i >= 1; i--) {
                decimalNumber *= radixTarget;

                integralPart = (int) decimalNumber;
                numTarget.append(Long.toString(integralPart, radixTarget));

                decimalNumber -= integralPart;
            }

            System.out.print("." + numTarget);
        }
    }

    public static boolean checkAnyPart(int radixOfNum, String anyPartInString, int radixTarget) {
        if (anyPartInString.matches("\\W") || radixOfNum <= 0 || radixTarget <= 0) {
            return true;
        } else {
            for (int i = 0; i < anyPartInString.length(); i++) {
                if (anyPartInString.charAt(i) >= 97 && anyPartInString.charAt(i) - 87 >= radixOfNum) { // if it is a letter
                    return true;
                } else if (radixOfNum != 1 && anyPartInString.charAt(i) <= 57 && anyPartInString.charAt(i) - 48 >= radixOfNum) { // if it is a number
                    return true;
                } else if (radixOfNum == 1 && anyPartInString.charAt(i) <= 57 && anyPartInString.charAt(i) - 48 > radixOfNum) { // if it is a number
                    return true;
                }
            }
        }

        return radixOfNum > 36 || radixTarget > 36;
    }
}
