package oncall.domain

enum class DayOfTheWeek(val korName: String) {
    Monday("월"),
    Tuesday("화"),
    Wednesday("수"),
    Thursday("목"),
    Friday("금"),
    Saturday("토"),
    Sunday("일");

    fun isWeekDay(): Boolean {
        return this == Monday || this == Tuesday || this == Wednesday || this == Thursday || this == Friday
    }

    fun after(day: Int): DayOfTheWeek {
        val twoWeek = DayOfTheWeek.entries + DayOfTheWeek.entries
        val index = twoWeek.indexOf(this) + day % 7
        return twoWeek[index]
    }

    companion object {
        fun of(value: String): DayOfTheWeek? {
            return DayOfTheWeek.entries.find { dayOfTheWeek -> dayOfTheWeek.korName == value }
        }

        fun String.toDayOfTheWeek(): DayOfTheWeek? {
            return DayOfTheWeek.entries.find { dayOfTheWeek -> dayOfTheWeek.korName == this }
        }
    }
}