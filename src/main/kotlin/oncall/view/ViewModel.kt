package oncall.view

import oncall.domain.DayOfTheWeek
import oncall.domain.Timesheet

class ViewModel {
    lateinit var timesheet: Timesheet

    fun makeTimesheet(month: Int, startDayOfWeek: DayOfTheWeek) {
        timesheet = Timesheet.of(month, startDayOfWeek)
        println(timesheet)
    }
}