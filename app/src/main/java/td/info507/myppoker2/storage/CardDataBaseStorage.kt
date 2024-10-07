package td.info507.myppoker2.storage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import td.info507.myppoker.storage.utility.DataBaseStorage
import td.info507.myppoker2.DataBaseHelper
import td.info507.myppoker2.model.Card

class CardDataBaseStorage(context: Context): DataBaseStorage<Card>(DataBaseHelper(context), "Card") {
    companion object {
        const val ID = 0
        const val VALUE = 1
        const val COLOR = 2
        const val DESCRIPTION = 3
    }

    override fun objectToValues(obj: Card): ContentValues{
        val values = ContentValues()

        values.put(Card.VALUE, obj.value)
        values.put(Card.DESCRIPTION, obj.description)
        values.put(Card.COLOR, obj.color)
        return values
    }

    override  fun cursorToObject(cursor: Cursor): Card {
        return Card (
            cursor.getInt(ID),
            cursor.getString(VALUE),
            cursor.getString(DESCRIPTION),
            cursor.getString(COLOR)
        )
    }
}