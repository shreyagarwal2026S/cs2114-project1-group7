import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** Tests for AppRunner's input validation methods: promptForInt and
 * promptForDate. Simulates typed input by feeding text into System.in and
 * captures printed output via System.out so re-prompt behavior can be checked.
 * 
 * @author scottpost
 * @version Sep 20, 2026
 */
public class AppRunnerTest
    extends TestCase
{
    // ~ Fields ................................................................
    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream outContent;

    // ~ Constructors ..........................................................
    public void setUp()
    {
        originalIn = System.in;
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }


    // ~Public Methods ........................................................
    /**
     * Restores the real System.in and System.out after each test so other tests
     * (and the console) aren't affected.
     */
    public void tearDown()
    {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }


    /**
     * Points System.in at the given text, as if the user had typed it, and
     * returns a fresh AppRunner built against that input.
     * 
     * @param typedInput
     *            the text to simulate the user typing, including newlines
     *            between separate entries
     * @return a new AppRunner reading from the simulated input
     */
    private AppRunner runnerWithInput(String typedInput)
    {
        System.setIn(new ByteArrayInputStream(typedInput.getBytes()));
        return new AppRunner();
    }


    /**
     * A single valid integer should be returned immediately, with no re-prompt.
     */
    public void testPromptForIntValidInput()
    {
        AppRunner runner = runnerWithInput("5\n");
        assertEquals(5, runner.promptForInt("Enter a number: "));
    }


    /**
     * Non numeric input should trigger a re-prompt, and the next valid line
     * should be the value returned
     */
    public void testPromptForIntRepromptsInvalidInput()
    {
        AppRunner runner = runnerWithInput("abc\n5\n");
        assertEquals(5, runner.promptForInt("Enter a number: "));
        assertTrue(outContent.toString().contains("not a number"));
    }


    /**
     * A single valid date string should parse correctly with no re-prompt.
     */
    public void testPromptForDateValidInput()
    {
        AppRunner runner = runnerWithInput("2026-01-01\n");
        assertEquals(
            LocalDate.of(2026, 1, 1),
            runner.promptForDate("Enter a date: "));
    }


    /**
     * Malformed date text should trigger a re-prompt, and the next valid line
     * should be the date returned.
     */
    public void testPromptForDateRepromptsInvalidInput()
    {
        AppRunner runner = runnerWithInput("not-a-date\n2026-01-01\n");
        assertEquals(
            LocalDate.of(2026, 1, 1),
            runner.promptForDate("Enter a date: "));
        assertTrue(outContent.toString().contains("valid date"));
    }
}
