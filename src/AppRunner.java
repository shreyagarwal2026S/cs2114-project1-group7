import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * // -------------------------------------------------------------------------
 * /** The only class that talks directly to the user. Shows the menu, reads and
 * validates input, and re prompts when input is invalid.
 * 
 * @author scottpost
 * @version Sep 20, 2026
 */
public class AppRunner
{
    // ~ Fields ................................................................
    private Calendar calendar;
    private Scanner input;

    // ~ Constructors ..........................................................
    /**
     * Constructs an AppRunner with a fresh, empty Calendar and a Scanner
     * reading from standard input.
     */
    public AppRunner()
    {
        calendar = new Calendar();
        input = new Scanner(System.in);
    }


    // ~Public Methods ........................................................
    /**
     * Runs the main menu loop. Shows options, reads the user's choice, and
     * dispatches to the appropriate action until the user chooses to quit.
     */
    public void run()
    {
        boolean running = true;
        while (running)
        {
            System.out.println("1. Add a habit");
            System.out.println("2. Log a completion");
            System.out.println("3. View streaks");
            System.out.println("4. Quit");
            System.out.print("Choose an option: ");
        }

        int choice = promptForInt("Choose an option: ");

        if (choice == 1)
        {
            addHabitFlow();
        }
    }


    /**
     * Reads a line of text from the user and parses it into a LocalDate,
     * re-prompting if the text is not a valid date.
     * 
     * @param prompt
     *            The message to show the user
     * @return a valid LocalDate entered by the user
     */
    public LocalDate promptForDate(String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            String line = input.nextLine();
            try
            {
                return LocalDate.parse(line);
            }
            catch (DateTimeParseException e)
            {
                System.out.println(
                    "That doesn't look like a valid date (expected YYYY-MM-DD). Try again.");
            }

        }
    }


    /**
     * Reads a line of text from the user and parses it into an int, re-prompts
     * if the text is not a valid integer.
     * 
     * @param prompt
     *            The message to show the user
     * @return a valid int entered by the user
     */
    public int promptForInt(String prompt)
    {
        while (true)
        {
            String line = input.nextLine();
            try
            {
                return Integer.parseInt(line.trim());
            }
            catch (NumberFormatException e)
            {
                System.out.print("That's not a number. " + prompt);
            }
        }
    }

    // ~Private Methods ........................................................


    /**
     * Walks the user through adding a new habit, re-prompting on invalid
     * name/frequency and on duplicate habit names.
     */
    private void addHabitFlow()
    {
        System.out.print("Habit name: ");
        String name = input.nextLine();

        System.out.print("Frequency (1 = daily, 2 = N times/week): ");
        int type = promptForInt("Frequency (1 = daily, 2 = N times/week): ");

        FrequencyRule rule;
        if (type == 2)
        {
            int timesPerWeek = promptForInt("How many times per week?: ");
            try
            {
                rule = new WeeklyCountRule(timesPerWeek);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Times per week must be between 1 and 7.");
                return;
            }
        }
        else
        {
            rule = new DailyRule();
        }
        try
        {
            Habit h = new Habit(name, rule);
            if (!calendar.addHabit(h))
            {
                System.out.println("A habit with that name already exists.");
            }
            else
            {
                System.out.println("Added " + name + ".");
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Habit name can't be empty.");
        }
    }


    /**
     * Walks the user through logging a completion for an existing habit.
     * Re-prompts on unknown habit names, future dates, and duplicate
     * completions.
     */
    private void logCompletionFlow()
    {
        System.out.print("Habit name: ");
        String name = input.nextLine();
        Habit h = calendar.getHabitByName(name);
        if (h == null)
        {
            System.out.println("No habit with that name exists.");
            return;
        }

        LocalDate date = promptForDate("Date completed (YYY-MM-DD): ");
        if (!calendar.logCompletion(h, date))
        {
            System.out.println(
                "Couldn't log that coompletion (future date or already logged).");

        }
        else
        {
            System.out.println("Logged.");
        }
    }

    /**
     * Displays the current and longest streak for a habit the user chooses by
     * name.
     */
    private void viewStreaksFlow()
    {
        System.out.print("Habit name: ");
        String name = input.nextLine();
        Habit h = calendar.getHabitByName(name);
        if (h == null)
        {
            System.out.println("No habit with that name exists.");
            return;
        }

        StreakCalculator calc = new StreakCalculator();
        int current = calc.currentStreak(h, calendar);
        int longest = calc.longestStreak(h, calendar);
        System.out.println("Current streak: " + current);
        System.out.println("Longest streak: " + longest);
    }

}
