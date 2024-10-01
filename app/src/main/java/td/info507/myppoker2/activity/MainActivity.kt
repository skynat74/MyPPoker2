package td.info507.myppoker2.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.myppoker2.adapter.CardAdapter
import td.info507.myppoker2.R
import td.info507.myppoker2.dialog.CardDialogFragment

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CARD = "EXTRA_CARD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = findViewById<RecyclerView>(R.id.card_list)
        list.adapter = object: CardAdapter(applicationContext) {
            override fun onClickListener(view: View) {
                var intent = Intent(applicationContext, CardActivity::class.java).apply{
                    putExtra(EXTRA_CARD, view.tag as Int)
                }
                startActivity(intent)
            }
        }

        findViewById<FloatingActionButton>(R.id.add_card).setOnClickListener() {
            CardDialogFragment().show(supportFragmentManager, null)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.profile -> startActivity(Intent(applicationContext, ProfileActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}