package oncall.view

import camp.nextstep.edu.missionutils.Console.readLine
import oncall.domain.DayOfTheWeek
import oncall.domain.DayOfTheWeek.Companion.toDayOfTheWeek
import oncall.domain.Staff

object InputView {
    fun makeTimesheet(): Pair<Int, DayOfTheWeek> {
        val line = readLine()
        if (!line.contains(',') || line.split(',').size != 2) throw IllegalArgumentException("[ERROR] 쉼표(,)를 통해 구분해주세요. Ex: 3,화")
        val month =
            line.split(',').first().toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 월은 1부터 12 사이의 숫자로 표현해주세요.")
        val dayOfTheWeek: DayOfTheWeek = line.split(',').last().toDayOfTheWeek()
            ?: throw IllegalArgumentException("[ERROR] 요일은 월, 화, 수, 목, 금, 토, 일 중 하나만 입력해주세요.")
        return month to dayOfTheWeek
    }

    fun readShiftOrder(): List<Staff> {
        val line = readLine()
        if (!line.contains(',')) throw IllegalArgumentException("[ERROR] 쉼표(,)를 통해 구분해주세요. Ex: 3,화")
        if (line.split(',').size !in 5..35) throw IllegalArgumentException("[ERROR] 직원은 5명에서 35명까지만 입력 가능합니다.")
        return line.split(',').map { nickname -> Staff(nickname) }
    }
}