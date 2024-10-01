package td.info507.myppoker2.storage

import td.info507.myppoker2.model.Card

class CardNoneStorage: Storage<Card> {
    override fun insert(obj: Card): Int = 0

    override fun size(): Int = 0

    override fun find(id: Int): Card? = null

    override fun findAll(): List<Card> = emptyList()

    override fun delete(id: Int) = Unit

    override fun update(id: Int, obj: Card) = Unit

}