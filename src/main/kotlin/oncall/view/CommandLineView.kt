package oncall.view

import oncall.domain.DayOfTheWeek

class CommandLineView : View {
    private val viewModel: ViewModel = ViewModel()

    override fun makeTimesheet() {
        var again = true
        while (again) {
            runCatching {
                OutputView.makeTimesheet()
                val (month: Int, startDayOfWeek: DayOfTheWeek) = InputView.makeTimesheet()
                viewModel.makeTimesheet(month, startDayOfWeek)
            }.onSuccess { again = false }.onFailure { OutputView.showError(it) }
        }
    }

    override fun assignWorkers() {
        var again = true
        while (again) {
            runCatching {
                OutputView.readWeekdayShiftOrder()
                val weekdayShiftOrder = InputView.readShiftOrder()
                OutputView.readHolidayShiftOrder()
                val holidayShiftOrder = InputView.readShiftOrder()
                viewModel.assignWorkers(weekdayShiftOrder, holidayShiftOrder)
            }.onSuccess { again = false }.onFailure { OutputView.showError(it) }
        }
    }

    override fun showTimesheet() {
        OutputView.showTimesheet(viewModel.timesheet)
    }
}