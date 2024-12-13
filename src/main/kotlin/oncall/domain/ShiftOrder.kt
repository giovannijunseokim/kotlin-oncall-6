package oncall.domain

class ShiftOrder(
    val order: List<Staff>,
) {
    var position: Int = 0
        private set
    val tempOrder: MutableList<Staff> = mutableListOf()

    fun peek(): Staff {
        val staff = order[position]
        position = (position + 1) % order.size
        return staff
    }
}