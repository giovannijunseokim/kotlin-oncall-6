package oncall.view

import oncall.domain.DayOfTheWeek

class CommandLineView : View {
    val viewModel: ViewModel = ViewModel()

    override fun makeTimesheet() {
        OutputView.makeTimesheet()
        val (month: Int, startDayOfWeek: DayOfTheWeek) = InputView.makeTimesheet()
        viewModel.makeTimesheet(month, startDayOfWeek)

    }

    override fun readShift() {
        TODO("Not yet implemented")
    }

    override fun showTimeSheet() {
        TODO("Not yet implemented")
    }
}