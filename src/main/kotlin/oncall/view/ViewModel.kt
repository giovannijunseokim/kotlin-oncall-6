package oncall.view

import oncall.domain.DayOfTheWeek
import oncall.domain.ShiftOrder
import oncall.domain.Staff
import oncall.domain.Timesheet

class ViewModel {
    lateinit var timesheet: Timesheet
    private lateinit var weekdayShiftOrder: ShiftOrder
    private lateinit var holidayShiftOrder: ShiftOrder

    fun makeTimesheet(month: Int, startDayOfWeek: DayOfTheWeek) {
        timesheet = Timesheet.create(month, startDayOfWeek)
    }

    fun assignWorkers(weekdayStaffs: List<Staff>, holidayStaffs: List<Staff>) {
        setShiftOrders(weekdayStaffs, holidayStaffs)
        timesheet.days.forEachIndexed { index, day: Timesheet.Day ->
            if (index == 0) {
                if (day.isWeekday && !day.isHoliday(timesheet.month)) {
                    day.staff = weekdayShiftOrder.peek()
                } else {
                    day.staff = holidayShiftOrder.peek()
                }
                return@forEachIndexed
            }

            val yesterdayStaff = timesheet.days[index - 1].staff
            if (day.isWeekday && !day.isHoliday(timesheet.month)) {
                var todayStaff = if (weekdayShiftOrder.tempOrder.isNotEmpty()) weekdayShiftOrder.tempOrder.removeFirst()
                else weekdayShiftOrder.peek()
                if (yesterdayStaff == todayStaff) {
                    weekdayShiftOrder.tempOrder.add(todayStaff)
                    todayStaff = weekdayShiftOrder.peek()
                }
                day.staff = todayStaff
            } else {
                var todayStaff =
                    if (holidayShiftOrder.tempOrder.isNotEmpty()) holidayShiftOrder.tempOrder.removeFirst() else holidayShiftOrder.peek()
                if (yesterdayStaff == todayStaff) {
                    holidayShiftOrder.tempOrder.add(todayStaff)
                    todayStaff = holidayShiftOrder.peek()
                }
                day.staff = todayStaff
            }
        }
    }

    private fun setShiftOrders(weekdayShiftOrder: List<Staff>, holidayShiftOrder: List<Staff>) {
        this.weekdayShiftOrder = ShiftOrder(weekdayShiftOrder)
        this.holidayShiftOrder = ShiftOrder(holidayShiftOrder)
    }
}