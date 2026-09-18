import java.time.LocalDate;
import java.util.List;


public abstract class FrequencyRule
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................

    public abstract boolean isSatisfiedForPeriod(List<LocalDate> completions, 
        LocalDate periodStart, LocalDate periodEnd);
    
    public abstract String describe();
    
}
