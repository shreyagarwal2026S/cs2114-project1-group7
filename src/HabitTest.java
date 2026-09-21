

import student.TestCase;


/**
 * // -------------------------------------------------------------------------
/**
 *  Tests the Habit class
 * 
 *  @author Lena Birye
 *  @version 09.20.2026
 */
public class HabitTest
    extends TestCase
{
    private Habit habit;
    private DailyRule dailyRule;
    
    /**
     * Creates fresh objects before each test.
     */
    public void setUp()
    {
        dailyRule = new DailyRule();
        habit = new Habit("Gym", dailyRule);
    }
    /**
     * Tests that a habit can be created with a valid
     * name and frequency rule.
     */
    public void testConstructor()
    {
        assertNotNull(habit);
    }
    /**
     * Tests that getName returns the habits name
     */
    public void testGetName()
    {
        assertEquals("Gym", habit.getName());
    }
    
    /**
     * Tests that getRule returns the habits frequency rule
     */
    public void testGetRule()
    {
        assertEquals(dailyRule, habit.getRule());
    }
    
    /**
     * Tests that getDateCreated returns a creation date.
     */
    public void testGetDateCreated()
    {
        assertNotNull(habit.getDateCreated());
    }
    
    /**
     * Tests that a null habit name throws an exception.
     */
    public void testNullNameThrowsException()
    {
        Exception thrown = null;
        
        try
        {
            new Habit(null, dailyRule);
        }
        catch (IllegalArgumentException e)
        {
            thrown = e;
        }
        
        assertNotNull(thrown);
    }
    
    /**
     * Tests that a blank habit name throws an exception.
     */
    public void testBlankNameThrowsException()
    {
        Exception thrown = null;
        
        try
        {
            new Habit(" ", dailyRule);
        }
        catch (IllegalArgumentException e)
        {
            thrown = e;
        }
        
        assertNotNull(thrown);
    }
    
    /**
     * Tests that a habit name longer than 100 
     * characters throws an exception.
     */
    public void testLongNameThrowsException()
    {
        Exception thrown = null;
        String longName = "a".repeat(101);
        
        try
        {
            new Habit(longName, dailyRule);
        }
        catch (IllegalArgumentException e)
        {
            thrown = e;
        }
        
        assertNotNull(thrown);
    }
    
    /**
     * Tests that a null frequency rule throws an exception.
     */
    public void testNullRuleThrowsException()
    {
        Exception thrown = null;
        
        try
        {
            new Habit("Gym", null);
        }
        catch(IllegalArgumentException e)
        {
            thrown = e;
        }
        
        assertNotNull(thrown);
    }

}
