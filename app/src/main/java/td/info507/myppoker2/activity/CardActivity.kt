package td.info507.myppoker2.activity

import CardStorage
import CardStorage.getLogin
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import td.info507.myppoker2.R

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val id = intent.getIntExtra(MainActivity.EXTRA_CARD, 0)
        val card = CardStorage.get(applicationContext).find(id)

        findViewById<ConstraintLayout>(R.id.card_layout).setBackgroundColor(Color.parseColor(card?.color))
        findViewById<TextView>(R.id.card_value).text = card?.value
        findViewById<TextView>(R.id.card_description).text = card?.description
        findViewById<TextView>(R.id.card_login).text = getLogin(applicationContext)
    }
}