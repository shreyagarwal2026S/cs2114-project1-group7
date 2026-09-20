
import java.time.LocalDate;


/**
 * // -------------------------------------------------------------------------
/**
 *  Represents a habit with a name, frequency
 *  rule, and creation date.
 * 
 *  @author Lena Birye
 *  @version 09.20.2026
 */

public class Habit
{
    // ~ Fields ................................................................

    private String name;
    private FrequencyRule rule;
    private LocalDate dateCreated;

    // ~ Constructors ..........................................................

    /**
     * Creates a habit with the given name and frequency rule.
     */
    public Habit(String name, FrequencyRule rule)
    {
        if (name == null || name.isBlank() || name.length() > 100)
        {
            throw new IllegalArgumentException();
        }

        if (rule == null)
        {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.rule = rule;
        this.dateCreated = LocalDate.now();
    }
    // ~Public Methods ........................................................


    /**
     * Returns the name of the habit
     * @return the habits name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Returns the frequency rule for this habit
     * @return the habits frequency rule
     */
    public FrequencyRule getRule()
    {
        return rule;
    }


    /**
     * Returns the date when this habit was created
     * @return the habits creation date
     */
    public LocalDate getDateCreated()
    {
        return dateCreated;
    }
}
