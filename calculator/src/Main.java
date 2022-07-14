import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String [] args) throws InputError {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        if (userInput.equals(""))
            throw new InputError("String can not be empty!");

        System.out.println(calc(userInput));
    }
    public static String calc(String input) throws InputError {
        ProcessedData.checkInput(input);
        return ProcessedData.outputResult(ProcessedData.calculator());
    }
}

class ProcessedData {

    static String operator, notation;
    static int firstNumber, secondNumber;
    static List<String> arabicNumerals = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    static List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
    static void checkInput(String userString) throws InputError {
        String [] inputArr = userString.split(" ");
        if (inputArr.length == 3 && operators.contains(inputArr[1])){
            operator = inputArr[1];
            if (arabicNumerals.contains(inputArr[0]) && arabicNumerals.contains(inputArr[2])){
                firstNumber = Integer.parseInt(inputArr[0]);
                secondNumber = Integer.parseInt(inputArr[2]);
                notation = "Arabic";
            }
            else {
                if (ConversionToArabic.romanNumerals.contains(inputArr[0]) && ConversionToArabic.romanNumerals.contains(inputArr[2])){
                    firstNumber = Integer.parseInt(arabicNumerals.get(ConversionToArabic.romanNumerals.indexOf(inputArr[0])));
                    secondNumber = Integer.parseInt(arabicNumerals.get(ConversionToArabic.romanNumerals.indexOf(inputArr[2])));
                    notation = "Roman";
                }
                else {
                    throw new InputError("Input Error");
                }
            }
        }
        else {
            throw new InputError("Input Error");
        }
    }
    static String outputResult(int resultOut) throws InputError {
        if (notation.equals("Arabic")){
            return String.valueOf(resultOut);
        }
        else {
            if (notation.equals("Roman") && resultOut > 0){
                return ConversionToArabic.convert(resultOut);
            }
            else {
                throw new InputError("In the Roman numeral system, the result cannot be less than 1");
            }
        }
    }
    static int calculator() {
        int result = 0;
        
        switch (ProcessedData.operator){
            case "+" :
                result = firstNumber + secondNumber;
                break;
            case "-" :
                result = firstNumber - secondNumber;
                break;
            case "*" :
                result = firstNumber * secondNumber;
                break;
            case "/" :
                result = firstNumber / secondNumber;
                break;
        }
        return result;
    }

}



class ConversionToArabic {
    static List<String> romanResult = new ArrayList<>(Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XII", "XIV",
            "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
            "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
            "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
            "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
            "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
            "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI",
            "XCVII", "XCVIII", "XCIX", "C"));
    static List<String> romanNumerals = new ArrayList<>(Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"));

    static String convert (int arabicResult){
        return romanResult.get(arabicResult - 1);
    }
}

class InputError extends Exception {
    public InputError(String description) {
        super(description);
    }
}