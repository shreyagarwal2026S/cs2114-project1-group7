
import java.time.LocalDate;
import java.util.ArrayList;
import student.TestCase;


/**
 * // -------------------------------------------------------------------------
/**
 *  Tests the DailyRule class.
 * 
 *  @author Lena Birye
 *  @version 09.20.26
 */
public class DailyRuleTest extends TestCase
{

    private DailyRule dailyRule;
    private ArrayList<LocalDate> completions;
    
    /**
     * Creates fresh objects before each test.
     */
    public void setUp()
    {
        dailyRule = new DailyRule();
        completions = new ArrayList<LocalDate>();
    }
    
    /**
     * Tests that the rule is satisfied when the habit
     * was completed on the day being checked.
     */
    public void testIsSatisfiedForPeriodTrue()
    {
        LocalDate date = LocalDate.of(2026, 9, 20);
        completions.add(date);
        
        assertEquals(true, dailyRule.isSatisfiedForPeriod(
            completions, date, date));
    }
    
    /**
     * Tests that rule is not satisfied when the habit
     * was not completed on the day being checked.
     */
    public void testIsSatisfiedForPeriodFalse()
    {
        LocalDate completedDate = LocalDate.of(2026, 9, 19);
        LocalDate checkedDate = LocalDate.of(2026, 9, 20);
        
        completions.add(completedDate);
        
        assertEquals(false, dailyRule.isSatisfiedForPeriod(
            completions, checkedDate, checkedDate));
        
        
    }
    
    /**
     * Tests that describe returns Daily.
     */
    public void testDescribe()
    {
        assertEquals("Daily", dailyRule.describe());
    }
    
    

}
