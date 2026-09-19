import java.time.LocalDate;

public class Habit
{
    // ~ Fields ................................................................

    private String name;
    private FrequencyRule rule;
    private LocalDate dateCreated;

    // ~ Constructors ..........................................................

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


    public String getName()
    {
        return name;
    }


    public FrequencyRule getRule()
    {
        return rule;
    }


    public LocalDate getDateCreated()
    {
        return dateCreated;
    }
}
