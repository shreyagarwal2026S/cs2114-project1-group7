
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Tests the StreakCalculator class.
 *
 * @author Shrey Agarwal
 * @version Sep 20, 2026
 */
public class StreakCalculatorTest
    extends student.TestCase
{
    private Calendar calendar;
    private StreakCalculator calculator;

    /**
     * Sets up the objects used by each test.
     */
    public void setUp()
    {
        calendar = new Calendar();
        calculator = new StreakCalculator();
    }


    /**
     * Tests a current daily streak.
     */
    public void testCurrentStreakDaily()
    {
        Habit habit = new Habit("Study", new DailyRule());

        calendar.addHabit(habit);

        LocalDate today = LocalDate.now();

        calendar.logCompletion(habit, today);
        calendar.logCompletion(habit, today.minusDays(1));
        calendar.logCompletion(habit, today.minusDays(2));

        assertEquals(3, calculator.currentStreak(habit, calendar));
    }


    /**
     * Tests a current streak when there are no completions.
     */
    public void testCurrentStreakNoCompletions()
    {
        Habit habit = new Habit("Study", new DailyRule());

        calendar.addHabit(habit);

        assertEquals(0, calculator.currentStreak(habit, calendar));
    }


    /**
     * Tests the longest daily streak.
     */
    public void testLongestStreakDaily()
    {
        Habit habit = new Habit("Study", new DailyRule());

        calendar.addHabit(habit);

        LocalDate today = LocalDate.now();

        calendar.logCompletion(habit, today);
        calendar.logCompletion(habit, today.minusDays(1));
        calendar.logCompletion(habit, today.minusDays(2));

        calendar.logCompletion(habit, today.minusDays(4));
        calendar.logCompletion(habit, today.minusDays(5));

        assertEquals(3, calculator.longestStreak(habit, calendar));
    }


    /**
     * Tests the longest streak when there are no completions.
     */
    public void testLongestStreakNoCompletions()
    {
        Habit habit = new Habit("Study", new DailyRule());

        calendar.addHabit(habit);

        assertEquals(0, calculator.longestStreak(habit, calendar));
    }


    /**
     * Tests the current streak for a weekly habit.
     */
    public void testCurrentStreakWeekly()
    {
        Habit habit = new Habit("Gym", new WeeklyCountRule(1));

        calendar.addHabit(habit);

        LocalDate monday = LocalDate.now()
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        calendar.logCompletion(habit, monday);

        assertEquals(1, calculator.currentStreak(habit, calendar));
    }


    /**
     * Tests the longest streak for a weekly habit.
     */
    public void testLongestStreakWeekly()
    {
        Habit habit = new Habit("Gym", new WeeklyCountRule(1));

        calendar.addHabit(habit);

        LocalDate monday = LocalDate.now()
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        calendar.logCompletion(habit, monday);
        calendar.logCompletion(habit, monday.minusWeeks(1));
        calendar.logCompletion(habit, monday.minusWeeks(2));
        calendar.logCompletion(habit, monday.minusWeeks(4));

        assertEquals(3, calculator.longestStreak(habit, calendar));
    }
}
