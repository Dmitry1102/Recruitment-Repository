package pl.proexe.junior.model.repository

import pl.proexe.junior.R
import pl.proexe.junior.model.data.DayTile
import java.util.*

class TimeRepository {

    fun getDayTiles() = listOf(
        DayTile(addDaysToCurrentCalendar(-2), R.string.day_before_yesterday),
        DayTile(addDaysToCurrentCalendar(-1), R.string.yesterday),
        DayTile(addDaysToCurrentCalendar(0), R.string.today),
        DayTile(addDaysToCurrentCalendar(1), R.string.tomorrow),
        DayTile(addDaysToCurrentCalendar(2), R.string.day_after_tomorrow)
    )

    private fun addDaysToCurrentCalendar(amount: Int): Long {
        val cal = getCurrentCalendar()
        cal.add(Calendar.DAY_OF_YEAR, amount)
        return cal.time.time
    }

    fun getCurrentTime(): Date = getCurrentCalendar().time

    private fun getCurrentCalendar() = Calendar.getInstance()
}