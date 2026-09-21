

import java.time.LocalDate;
import java.util.List;

/**
 * // -------------------------------------------------------------------------
/**
 *  Represents a rule that determines how often a habit 
 *  must be completed.
 * 
 *  @author Lena Birye
 *  @version 09.20.2026
 */
public abstract class FrequencyRule
{
  
    /**
     * Determines whether the habits completion requirement
     * was satisfied during the given period.
     * @param completions the dates when the habit was completed
     * @param periodStart the first date of the period
     * @param periodEnd the last date of the period
     * @return true if the rule was satisfied, false otherwise
     */
    public abstract boolean isSatisfiedForPeriod(List<LocalDate> completions, 
        LocalDate periodStart, LocalDate periodEnd);
    
    /**
     * Returns a description of the frequency rule.
     * @return a description of the rule
     */
    public abstract String describe();
    
}
