import java.time.LocalDate;
import java.util.List;

public class DailyRule
    extends FrequencyRule
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................

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
