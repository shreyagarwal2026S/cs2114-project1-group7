package src;

import student.TestCase;

public class HabitTest
    extends TestCase
{
    private Habit habit;
    private DailyRule dailyRule;
    
    public void setUp()
    {
        dailyRule = new DailyRule();
        habit = new Habit("Gym", dailyRule);
    }
    
    public void testGetName()
    {
        assertEquals("Gym", habit.getName());
    }
    
    public void testGetRule()
    {
        assertEquals(dailyRule, habit.getRule());
    }
    
    public void testGetDateCreated()
    {
        assertNotNull(habit.getDateCreated());
    }
    
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
    
    public void testNullRuleThrowsException()
    {
        Exception thrown = null;
        
        try
        {
            new Habit("Gym", null);
        }
        catch(IllegalArgument Exception e)
        {
            thrown = e;
        }
        
        assertNotNull(thrown);
    }

}
