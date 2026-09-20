

import java.time.LocalDate;
import java.util.ArrayList;
import student.TestCase;



/**
 * // -------------------------------------------------------------------------
/**
 *  Tests the WeeklyCountRule class.
 * 
 *  @author Lena Birye
 *  @version 09.20.26
 */
public class WeeklyCountRuleTest extends TestCase
{
    private WeeklyCountRule weeklyRule;
    private ArrayList<LocalDate> completions;
    
    /**
     * Creates fresh objects before each test.
     */
    public void setUp()
    {
        weeklyRule = new WeeklyCountRule(3);
        completions = new ArrayList<LocalDate>();
    }
    
    /**
     * Tests that a valid weekly rule can be created.
     */
    public void testConstructor()
    {
        assertNotNull(weeklyRule);
    }
    
    /**
     * Tests that a frequency less than 1
     * throws an exception.
     */
    public void testFrequencyTooLow()
    {
        Exception thrown = null;
        
        try
        {
            new WeeklyCountRule(0);
        }
        catch (IllegalArgumentException e)
        {
            thrown = e;
        }
        
        assertNotNull(thrown);
    }
    
    /**
     * Tests that a frequency greater than 7 throws
     * an exception.
     */
    public void testFrequencyTooHigh()
    {
        Exception thrown = null;
        
        try
        {
            new WeeklyCountRule(8);
        }
        catch (IllegalArgumentException e)
        {
            thrown = e;
        }
        
        assertNotNull(thrown);
    }
    
    /**
     * Tests that the rule is satisfied when there are
     * enough completions during the period.
     */
    public void testIsSatisfiedForPeriodTrue()
    {
        LocalDate periodStart = LocalDate.of(2026, 9, 14);
        LocalDate periodEnd = LocalDate.of(2026, 9, 20);
        
        completions.add(LocalDate.of(2026, 9, 14));
        completions.add(LocalDate.of(2026, 9, 16));
        completions.add(LocalDate.of(2026, 9, 18));
        
        assertEquals(true, weeklyRule.isSatisfiedForPeriod(
            completions, periodStart, periodEnd));
        
    }
    /**
     * Tests that the rule is not satisfied when there are
     * not enough completions during the period.
     */
    public void testIsSatisfiedForPeriodFalse()
    {
        LocalDate periodStart = LocalDate.of(2026, 9, 14);
        LocalDate periodEnd = LocalDate.of(2026, 9, 20);
        
        completions.add(LocalDate.of(2026, 9, 14));
        completions.add(LocalDate.of(2026, 9, 16));
        
        assertEquals(false, weeklyRule.isSatisfiedForPeriod(
            completions, periodStart, periodEnd));
        
        
    }
    
    /**
     * Tests that a completion outside the period
     * is not counted.
     */
    public void testCompletionsOutsidePeriod()
    {
        LocalDate periodStart = LocalDate.of(2026, 9, 14);
        LocalDate periodEnd = LocalDate.of(2026, 9, 20);
        
        completions.add(LocalDate.of(2026, 9, 14));
        completions.add(LocalDate.of(2026, 9, 16));
        completions.add(LocalDate.of(2026, 9, 22));
        
        assertEquals(false, weeklyRule.isSatisfiedForPeriod(
            completions, periodStart, periodEnd));
        
    }
    
    /**
     * Tests that describe returns the correct description.
     */
    public void testDescribe()
    {
        assertEquals("3 times/week", weeklyRule.describe());
    }
    
    
    
    
    

}
