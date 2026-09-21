package src;

import java.time.LocalDate;
import java.util.List;


/**
 * // -------------------------------------------------------------------------
/**
 *  Represents a frequency rule that requires a habit
 *  to be completed once per day.
 * 
 *  @author Lena Birye
 *  @version 09.20.2026
 */

public class DailyRule
    extends FrequencyRule
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................

    /**
     * Determines whether the habit was completed on
     * the day being checked.
     * @param completions the dates when the habit was completed
     * @param periodStart the day being checked
     * @param periodEnd the last date of the period
     * @return true if the habit was completed that day, false otherwise
     */
    public boolean isSatisfiedForPeriod(List<LocalDate> completions, 
        LocalDate periodStart, LocalDate periodEnd)
    {
        return completions.contains(periodStart);
    }
    
    public String describe()
    {
        return "Daily";
    }
}
