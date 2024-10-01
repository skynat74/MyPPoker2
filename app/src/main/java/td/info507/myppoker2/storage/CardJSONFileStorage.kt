package td.info507.myppoker2.storage

import android.content.Context
import org.json.JSONObject
import td.info507.myppoker2.model.Card
import td.info507.myppoker2.storage.utility.file.JSONFileStorage

class CardJSONFileStorage(context: Context): JSONFileStorage<Card>(context, "card") {

    override fun create(id: Int,obj: Card): Card {
        return Card(id, obj.value, obj.description, obj.color)
    }
    override fun objectToJson(id: Int, obj: Card): JSONObject {
        val json = JSONObject()
        json.put(Card.ID, obj.id)
        json.put(Card.VALUE, obj.value)
        json.put(Card.DESCRIPTION, obj.description)
        json.put(Card.COLOR, obj.color)
        return json
    }
    override  fun jsonToObject(json: JSONObject): Card {
        return Card(
            json.getInt(Card.ID),
            json.getString(Card.VALUE),
            json.getString(Card.DESCRIPTION),
            json.getString(Card.COLOR),
        )
    }
}