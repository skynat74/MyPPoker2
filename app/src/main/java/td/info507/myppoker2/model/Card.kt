package td.info507.myppoker2.model

class Card(val id: Int, val value: String, val description: String, val color: String) {
    companion object {
        const val ID = "id"
        const val VALUE = "value"
        const val DESCRIPTION = "description"
        const val COLOR = "color"
    }
}