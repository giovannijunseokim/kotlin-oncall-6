package oncall.view

import oncall.domain.Timesheet


object OutputView {
    fun makeTimesheet() {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
    }

    fun readWeekdayShiftOrder() {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
    }

    fun readHolidayShiftOrder() {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
    }

    fun showTimesheet(timesheet: Timesheet) {
        timesheet.days.forEach { day: Timesheet.Day ->
            println(
                "${timesheet.month}월 ${day.day}일 ${day.dayOfTheWeek.korName}${
                    if (day.isWeekday && day.isHoliday(
                            timesheet.month
                        )
                    ) "(휴일)" else ""
                } ${day.staff?.nickname}"
            )
        }
    }

    fun showError(throwable: Throwable) {
        println(throwable.message)
    }
}