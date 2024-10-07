package td.info507.myppoker2.request

import CardStorage
import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import td.info507.myppoker2.R
import td.info507.myppoker2.activity.Updatable
import td.info507.myppoker2.model.Card

class CardRequest(private val context: Context, updatable: Updatable) {
    init {
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET,
            " http://51.68.91.213/gr-0-0/cards.json",
            null,
            { res ->
                delete()
                insert(res.getJSONArray("cards"))
                updatable.update()
                Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show() },
            { err -> Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show() }
        )
        queue.add(request)
        queue.start()
    }

    private fun delete() {
        for (card in CardStorage.get(context).findAll()) {
            CardStorage.get(context).delete(card.id)
        }
    }

    private fun insert(array: JSONArray) {
        for (i in 0 until array.length()) {
            val card = array.getJSONObject(i)
            CardStorage.get(context).insert(
                Card(
                    0,
                    card.getString(Card.VALUE),
                    card.getString(Card.DESCRIPTION),
                    card.getString(Card.COLOR)
                )
            )
        }
    }
}