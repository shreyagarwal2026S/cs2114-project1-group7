import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.List;

/**
 * Calculates the current and longest streak for a habit. This class stores no
 * permanent state.
 *
 * @author Shrey Agarwal
 * @version Sep 20, 2026
 */
public class StreakCalculator
{
    /**
     * Calculates the current streak for a habit.
     *
     * @param h
     *            the habit whose streak is being calculated
     * @param cal
     *            the calendar containing completion history
     * @return the number of consecutive satisfied periods ending with the
     *             current period
     */
    public int currentStreak(Habit h, Calendar cal)
    {
        List<LocalDate> completions = cal.getCompletions(h);

        if (completions == null || completions.isEmpty())
        {
            return 0;
        }

        FrequencyRule rule = h.getRule();

        LocalDate periodStart = getPeriodStart(rule, LocalDate.now());

        LocalDate periodEnd = getPeriodEnd(rule, periodStart);

        int streak = 0;

        while (rule.isSatisfiedForPeriod(completions, periodStart, periodEnd))
        {
            streak++;

            periodStart = previousPeriod(rule, periodStart);

            periodEnd = getPeriodEnd(rule, periodStart);
        }

        return streak;
    }


    /**
     * Calculates the longest streak for a habit.
     *
     * @param h
     *            the habit whose streak is being calculated
     * @param cal
     *            the calendar containing completion history
     * @return the longest run of consecutive satisfied periods
     */
    public int longestStreak(Habit h, Calendar cal)
    {
        List<LocalDate> completions = cal.getCompletions(h);

        if (completions == null || completions.isEmpty())
        {
            return 0;
        }

        FrequencyRule rule = h.getRule();

        LocalDate earliestCompletion = Collections.min(completions);

        LocalDate periodStart = getPeriodStart(rule, earliestCompletion);

        LocalDate currentPeriodStart = getPeriodStart(rule, LocalDate.now());

        int current = 0;
        int longest = 0;

        while (!periodStart.isAfter(currentPeriodStart))
        {
            LocalDate periodEnd = getPeriodEnd(rule, periodStart);

            if (rule.isSatisfiedForPeriod(completions, periodStart, periodEnd))
            {
                current++;

                if (current > longest)
                {
                    longest = current;
                }
            }
            else
            {
                current = 0;
            }

            periodStart = nextPeriod(rule, periodStart);
        }

        return longest;
    }


    /**
     * Gets the starting date of a streak period.
     *
     * @param rule
     *            the frequency rule being used
     * @param date
     *            a date inside the period
     * @return the beginning of the period
     */
    private LocalDate getPeriodStart(FrequencyRule rule, LocalDate date)
    {
        if (rule instanceof WeeklyCountRule)
        {
            return date
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        }

        return date;
    }


    /**
     * Gets the ending date of a streak period.
     *
     * @param rule
     *            the frequency rule being used
     * @param periodStart
     *            the first date of the period
     * @return the ending date of the period
     */
    private LocalDate getPeriodEnd(FrequencyRule rule, LocalDate periodStart)
    {
        if (rule instanceof WeeklyCountRule)
        {
            return periodStart.plusDays(6);
        }

        return periodStart;
    }


    /**
     * Gets the previous streak period.
     *
     * @param rule
     *            the frequency rule being used
     * @param periodStart
     *            the current period start
     * @return the beginning of the previous period
     */
    private LocalDate previousPeriod(FrequencyRule rule, LocalDate periodStart)
    {
        if (rule instanceof WeeklyCountRule)
        {
            return periodStart.minusWeeks(1);
        }

        return periodStart.minusDays(1);
    }


    /**
     * Gets the next streak period.
     *
     * @param rule
     *            the frequency rule being used
     * @param periodStart
     *            the current period start
     * @return the beginning of the next period
     */
    private LocalDate nextPeriod(FrequencyRule rule, LocalDate periodStart)
    {
        if (rule instanceof WeeklyCountRule)
        {
            return periodStart.plusWeeks(1);
        }

        return periodStart.plusDays(1);
    }
}
