import android.content.Context
import android.content.SharedPreferences
import td.info507.myppoker2.model.Card
import td.info507.myppoker2.storage.CardDataBaseStorage
import td.info507.myppoker2.storage.CardJSONFileStorage
import td.info507.myppoker2.storage.CardNoneStorage
import td.info507.myppoker2.storage.Storage

object CardStorage {
    private const val LOGIN = "login"
    private const val STORAGE = "storage"
    const val NONE = 0
    const val DATA_BASE = 1
    const val FILE_JSON = 2

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("td.info507.MyPPoker2.preferences", Context.MODE_PRIVATE)
    }

    fun getLogin(context: Context): String? {
        return getPreferences(context).getString(LOGIN, "")
    }

    fun setLogin(context: Context, prefLogin: String) {
        getPreferences(context).edit().putString(LOGIN, prefLogin).apply()
    }

    fun getStorage(context: Context): Int {
        return getPreferences(context).getInt(STORAGE, NONE)
    }

    fun setStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(STORAGE, prefStorage).apply()
    }

    fun get(context: Context): Storage<Card> {
        var storage: Storage<Card> = CardNoneStorage()
        when (getStorage(context)) {
            NONE -> storage = CardNoneStorage()
            DATA_BASE -> storage = CardDataBaseStorage(context)
            FILE_JSON -> storage = CardJSONFileStorage(context)
        }
        return storage
    }
}