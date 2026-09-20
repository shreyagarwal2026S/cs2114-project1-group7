import student.TestCase;
import java.time.LocalDate;
import java.util.*;

/**
 * // -------------------------------------------------------------------------
 * /** Write a one-sentence summary of your class here. Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 * 
 * @author scottpost
 * @version Sep 19, 2026
 */

public class CalendarTest
    extends TestCase
{
    // ~ Fields ................................................................
    private Calendar calendar;
    private Habit exercise;
    private Habit reading;

    // ~Public Methods ........................................................
    /**
     * Sets up a Calendar and two habits before each test.
     */
    public void setUp()
    {
        calendar = new Calendar();
        exercise = new Habit("Exercise", new DailyRule());
        reading = new Habit("Reading", new DailyRule());
    }


    /**
     * Adding a new habit should succeed.
     */
    public void testAddHabitSucceedsForNewHabit()
    {
        assertTrue(calendar.addHabit(exercise));
    }


    /**
     * Adding a habit whose name already exists should fail and should not
     * create a second entry.
     */
    public void testAddHabitFailsForDuplicateName()
    {
        calendar.addHabit(exercise);
        Habit anotherExercise = new Habit("Exercise", new DailyRule());
        assertFalse(calendar.addHabit(anotherExercise));
        assertEquals(1, countNonNull(calendar.getAllHabits()));
    }


    /**
     * Counts the non null entries in a Habit array. Needed because getAllHabits
     * would return the full array, regardless of if the slots have been used.
     * 
     * @param arr
     *            The array to count
     * @return the number of non null entries.
     */
    private int countNonNull(Habit[] arr)
    {
        int count = 0;
        for (Habit h : arr)
        {
            if (h != null)
            {
                count++;
            }
        }
        return count;
    }


    /**
     * A newly added habit should start with an empty completions list, not a
     * null one.
     */
    public void testNewHabitStartsWithEmptyCompletions()
    {
        calendar.addHabit(exercise);
        List<LocalDate> completions = calendar.getCompletions(exercise);
        assertTrue(completions.isEmpty());
    }


    /**
     * Adding more habits than the initial array capacity should trigger a
     * resize rather than fail/throw an exception.
     */
    public void testAddHabitResizesWhenArrayIsFull()
    {
        for (int i = 0; i < 11; i++)
        {
            calendar.addHabit(new Habit("Habit" + i, new DailyRule()));
        }
        assertEquals(11, countNonNull(calendar.getAllHabits()));
    }


    /**
     * Logging a completion for today (a valid, non future date should succeed).
     */
    public void testLogCompletionSucceedsForValidDate()
    {
        calendar.addHabit(exercise);
        assertTrue(calendar.logCompletion(exercise, LocalDate.now()));
    }


    /**
     * Logging a completion for a future date should fail
     */
    public void testLogCompletionFailsForFutureDate()
    {
        calendar.addHabit(exercise);
        assertFalse(
            calendar.logCompletion(exercise, LocalDate.now().plusDays(1)));
    }


    /**
     * Logging the same habit/date combination twice should fail the second
     * time.
     */
    public void testLogCompletionFailsForDuplicateDate()
    {
        calendar.addHabit(exercise);
        calendar.logCompletion(exercise, LocalDate.now());
        assertFalse(calendar.logCompletion(exercise, LocalDate.now()));
    }


    /**
     * A successfully logged completion should appear in that habit's completion
     * list.
     */
    public void testGetCompletionsReflectsLoggedDate()
    {
        calendar.addHabit(exercise);
        LocalDate today = LocalDate.now();
        calendar.logCompletion(exercise, today);
        assertTrue(calendar.getCompletions(exercise).contains(today));
    }


    /**
     * Looking up an existing habit by name should return that habit.
     */
    public void testGetHabitByNameReturnsHabit()
    {
        calendar.addHabit(exercise);
        assertEquals(exercise, calendar.getHabitByName("Exercise"));
    }


    /**
     * Looking up a habit by name that doesn't match a tracked habit should
     * return null rather than throwing an exception.
     */
    public void testGetHabitByNameReturnsNullUnknownHabit()
    {
        calendar.addHabit(exercise);
        assertNull(calendar.getHabitByName("Gym"));
    }


    /**
     * getAllHabits should return every habit thats been added, in the order
     * they were added.
     */
    public void testGetAllHabitsIncludesEveryHabit()
    {
        calendar.addHabit(exercise);
        calendar.addHabit(reading);
        Habit[] all = calendar.getAllHabits();
        assertEquals(exercise, all[0]);
        assertEquals(reading, all[1]);
    }
}
