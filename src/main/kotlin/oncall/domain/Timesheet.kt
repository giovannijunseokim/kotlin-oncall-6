package oncall.domain

data class Timesheet(
    val month: Int,
    val days: List<Day>,
) {
    data class Day(
        val day: Int,
        val dayOfTheWeek: DayOfTheWeek,
        var staff: Staff? = null,
    ) {
        fun isHoliday(month: Int): Boolean {
            return Holiday.isHoliday(month, day)
        }

        val isWeekday: Boolean
            get() = dayOfTheWeek.isWeekDay()
    }

    companion object {
        fun create(month: Int, startDayOfTheWeek: DayOfTheWeek): Timesheet {
            val daysCount = when (month) {
                2 -> 28
                4, 6, 9, 11 -> 30
                1, 3, 5, 7, 8, 10, 12 -> 31
                else -> throw IllegalStateException("[ERROR] 달은 1월부터 12월까지 있습니다.")

            }
            val days = (1..daysCount).map { day -> Day(day, startDayOfTheWeek.after(day - 1)) }
            return Timesheet(month, days)
        }
    }
}
