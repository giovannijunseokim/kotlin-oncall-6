package oncall.view

import oncall.domain.DayOfTheWeek
import oncall.domain.Timesheet

class CommandLineView : View {
    private val viewModel: ViewModel = ViewModel()

    override fun makeTimesheet() {
        var again = true
        while (again) {
            runCatching {
                OutputView.makeTimesheet()
                val (month: Int, startDayOfWeek: DayOfTheWeek) = InputView.makeTimesheet()
                viewModel.makeTimesheet(month, startDayOfWeek)
            }.onSuccess { again = false }.onFailure { println(it.message) }
        }
    }

    override fun assignWorkers() {
        OutputView.readWeekdayShiftOrder()
        val weekdayShiftOrder = InputView.readShiftOrder()
        OutputView.readHolidayShiftOrder()
        val holidayShiftOrder = InputView.readShiftOrder()
        viewModel.assignWorkers(weekdayShiftOrder, holidayShiftOrder)
    }

    override fun showTimesheet() {
        viewModel.timesheet.run {
            days.forEach { day: Timesheet.Day ->
                println("${month}월 ${day.day}일 ${day.dayOfTheWeek.korName}${if (day.isWeekday && day.isHoliday(month)) "(휴일)" else ""} ${day.staff?.nickname}")
            }
        }
    }
}