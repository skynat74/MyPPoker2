package td.info507.myppoker2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import td.info507.myppoker2.model.Card

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, "myppoker.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL (
            "CREATE TABLE Card(" +
                "${BaseColumns._ID} INTEGER," +
                "${Card.VALUE} TEXT," +
                "${Card.COLOR} TEXT," +
                "${Card.DESCRIPTION} TEXT," +
                "PRIMARY KEY(${BaseColumns._ID})" +
            ")"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}