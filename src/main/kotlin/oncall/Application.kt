package oncall

import oncall.view.CommandLineView
import oncall.view.View

fun main() {
    Application.run()
}

object Application {
    private val view: View = CommandLineView()
    fun run() {
        view.makeTimesheet()
        view.assignWorkers()
        view.showTimeSheet()
    }
}