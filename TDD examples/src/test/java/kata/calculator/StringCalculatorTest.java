package kata.calculator;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @Before
    public void setup() {
        calculator = new StringCalculator(3);
    }

    @Test
    public void itShouldTakeStringInInputAndAddThem() {
        //given
        String input = "1,2,3";
        //when
        int result = calculator.add(input);
        //then
        Assert.assertEquals(6, result);
    }

    @Test
    public void itShouldAcceptMaxThreeNumbersInInputElseReturnZero() {
        //given
        String input = "1,2,3,4";
        //when
        int result = calculator.add(input);
        //then
        Assert.assertEquals(0, result);
    }

    @Test
    public void itShouldHandleExceptionAndReturnZeroInCaseOfWrongInputNumber() {
        //given
        String input = "a,2,3";
        //when
        int result = calculator.add(input);
        //then
        Assert.assertEquals(0, result);
    }

    @Test
    public void itShouldAlsoAcceptNewlineDelimiterInInputStringAndThenAddNumbers() {
        //given
        String input = "1\n2\n3";
        //when
        int result = calculator.add(input);
        //then
        Assert.assertEquals(6, result);
    }

    @Test
    public void itShouldAcceptCustomDelimiterInFirstLineAndThenSplitNumbersBasedOnThatDelimiter() {
        //given
        String input = "//xx\n1xx2xx3";
        //when
        int result = calculator.add(input);
        //then
        Assert.assertEquals(6, result);
    }

    @Test(expected = NegativeInputException.class)
    public void itShouldNotAllowNegativeNumbersAndThrowException() {
        //given
        String input = "-1\n2\n-3";
        //when
        int result = calculator.add(input);
    }

    @Test
    public void itShouldThrowCorrectExceptionMessageForNegativeNumbers() {
        //given
        String input = "-1\n2\n-3";
        //when
        try {
            int result = calculator.add(input);
        } catch (NegativeInputException ex) {
            Assert.assertEquals("These numbers are not allowed in the input string since they are negative: -1 -3 ", ex.getMessage());
        }
    }
}
