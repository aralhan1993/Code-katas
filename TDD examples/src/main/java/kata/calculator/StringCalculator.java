package kata.calculator;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * String Calculator KATA
 */
public class StringCalculator {

    private final int maxNumbersAllowed;

    public StringCalculator(int maxNumbersAllowed) {
        this.maxNumbersAllowed = maxNumbersAllowed;
    }

    /**
     * Add  numbers passed to it in string format
     * @param input
     * @return
     */
    public int add(String input) {
        String[] numbers = splitInputString(input);
        if (!isValidInput(numbers)) return 0;
        return generateSum(numbers);
    }

    /**
     * Checks if passed in input is valid or not
     * @param numbers
     * @return
     */
    private boolean isValidInput(String[] numbers) {
        boolean isValidationSuccessful = false;
        if (!Objects.isNull(numbers) && (numbers.length <= maxNumbersAllowed) && applyPositiveNumberValidation(numbers))
            isValidationSuccessful = true;
        return isValidationSuccessful;
    }

    /**
     * validates that only positive numbers are allowed in input
     * @param numbers
     * @return
     */
    private boolean applyPositiveNumberValidation(String[] numbers) {
        StringBuilder negativeNumbers = new StringBuilder();
        for (String number : numbers) {
            if (number.startsWith("-")) {
                negativeNumbers.append(number).append(" ");
            }
        }
        if (!negativeNumbers.toString().isEmpty())
            throw new NegativeInputException("These numbers are not allowed in the input string since they are negative: " + negativeNumbers.toString());
        return Boolean.TRUE;
    }

    /**
     * Method to generate sum from given numbers
     * @param numbers
     * @return
     */
    private int generateSum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            try {
                sum += Integer.parseInt(number);
            } catch (NumberFormatException ex) {
                return 0;
            }
        }
        return sum;
    }

    /**
     * Splits the input string based on the delimiter passed
     * @param input
     * @return
     */
    private String[] splitInputString(String input) {
        String splitter = "\\n|,";
        Matcher matcher = Pattern.compile("^(//)(.*)\n").matcher(input);
        if (matcher.find()) {
            splitter = splitter.concat("|").concat(matcher.group(2));
            input = input.substring(matcher.end());
        }
        return input.split(splitter);
    }
}