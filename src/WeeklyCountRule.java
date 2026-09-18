import java.time.LocalDate;
import java.util.List;


public class WeeklyCountRule
    extends FrequencyRule
{
    //~ Fields ................................................................

    private int timesPerWeek;
    
    //~ Constructors ..........................................................

    public WeeklyCountRule(int timesPerWeek)
    {
        if (timesPerWeek < 1 || timesPerWeek > 7)
        {
            throw new IllegalArgumentException();
        }
        
        this.timesPerWeek = timesPerWeek;
    }
    //~Public  Methods ........................................................

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
    
    public String describe()
    {
        return timesPerWeek + "times/week";
    }
    
    
}
