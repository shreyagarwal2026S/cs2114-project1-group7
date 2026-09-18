import java.util.*;
import java.time.*;

/**
 * Owns and manages all habit and completion data for the app. This is the only
 * class that directly reads or writes the underlying habit array and completion
 * map. Every other class must go through this class's public methods to
 * interact with that data.
 * 
 * @author scottpost
 * @version Sep 14, 2026
 */
public class Calendar
{
    // ~ Fields ................................................................
    private Habit[] habits;
    private int habitCount;
    private Map<Habit, ArrayList<LocalDate>> completionsByHabit;

    // ~ Constructors ..........................................................
    /**
     * Constructs an empty Calendar with no habits and no logged completions.
     * The habit array starts at a small initial capacity and doubles in size
     * whenever it becomes full.
     */
    public Calendar()
    {
        habits = new Habit[10];
        habitCount = 0;
        completionsByHabit = new HashMap<>();
    }


    // ~Public Methods ........................................................
    /**
     * Adds a new habit, as long as no existing habit already has that name.
     * Resizes the internal habit array (doubling its capacity) if it is already
     * full. Also creates an empty completion list for the new habit.
     * 
     * @param h
     *            The habit to add
     * @return true if the habit was added, false if a habit with the same name
     *             already exists.
     */
    public boolean addHabit(Habit h)
    {
        if (getHabitByName(h.getName()) != null)
        {
            return false; // duplicate name
        }
        if (habitCount == habits.length)
        {
            Habit[] newHabits = new Habit[habits.length * 2];
            for (int i = 0; i < habitCount; i++)
            {
                newHabits[i] = habits[i];
            }
            habits = newHabits;
        }
        habits[habitCount] = h;
        habitCount++;
        completionsByHabit.put(h, new ArrayList<>());
        return true;
    }


    /**
     * Logs a completion for the given habit on the given date, as long as the
     * date is not in the future and has no already been logged for that habit.
     * 
     * @param h
     *            The habit being completed
     * @param date
     *            The date the habit was completed on
     * @return true if the completion was logged, false if the date is in the
     *             future or was already logged for this habit.
     */
    public boolean logCompletion(Habit h, LocalDate date)
    {
        if (date.isAfter(LocalDate.now()))
        {
            return false;
        }
        ArrayList<LocalDate> dates = completionsByHabit.get(h);
        if (dates.contains(date))
        {
            return false;
        }
        dates.add(date);
        return true;
    }


    /**
     * Returns the list of dates on which the given habit was completed.
     * 
     * @param h
     *            The habit to look up
     * @return the list of completion dates for that habit
     */
    public List<LocalDate> getCompletions(Habit h)
    {
        return completionsByHabit.get(h);
    }


    /**
     * Searches the tracked habits for one with the given name.
     * 
     * @param name
     *            The habit name to search for
     * @return the matching habit, or null if no habit with that name exists.
     */
    public Habit getHabitByName(String name)
    {
        for (int i = 0; i < habitCount; i++)
        {
            if (habits[i].getName().equals(name))
            {
                return habits[i];
            }
        }
        return null;
    }


    /**
     * Returns every habit currently being tracked.
     * 
     * @return an array of all tracked habits
     */
    public Habit[] getAllHabits()
    {
        return habits;
    }

}
