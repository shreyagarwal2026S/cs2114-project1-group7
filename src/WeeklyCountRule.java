

import java.time.LocalDate;
import java.util.List;


/**
 * // -------------------------------------------------------------------------
/**
 *  Represents a frequency rule that requires a habit
 *  to be completed a certain number of times per week.
 * 
 *  @author Lena Birye
 *  @version 09.20.2026
 */
public class WeeklyCountRule
    extends FrequencyRule
{
    //~ Fields ................................................................

    private int timesPerWeek;
    
    //~ Constructors ..........................................................

    /**
     * Creates a weekly frequency rule with the given
     * number of required completions
     * @param timesPerWeek the number of times the habit
     * must be completed each week
     * @throws IllegalArgumentException if timesPerWeek
     * is less than 1 or greater than 7
     */
    public WeeklyCountRule(int timesPerWeek)
    {
        if (timesPerWeek < 1 || timesPerWeek > 7)
        {
            throw new IllegalArgumentException();
        }
        
        this.timesPerWeek = timesPerWeek;
    }
    //~Public  Methods ........................................................

    /**
     * Determines whether the habit was completed enough
     * times during the given period.
     * @param completions the dates when the habit was completed
     * @param periodStart the first date of the period
     * @param periodEnd the last date of the period
     * @return true if the required number of completions 
     * was reached, false otherwise
     */
    public boolean isSatisfiedForPeriod(List<LocalDate> completions, 
        LocalDate periodStart, LocalDate periodEnd)
    {
        int count = 0;
        
        for (LocalDate date : completions)
        {
            if (!date.isBefore(periodStart) && !date.isAfter(periodEnd))
            {
                count++;
            }
        }
        
        return count >= timesPerWeek;
    }
    /**
     * Returns a description of this weekly rule.
     * @return a description
     */
    public String describe()
    {
        return timesPerWeek + " times/week";
    }
    
    
}
