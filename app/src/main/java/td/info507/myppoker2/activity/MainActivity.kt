package td.info507.myppoker2.activity

import CardStorage
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.myppoker2.adapter.CardAdapter
import td.info507.myppoker2.R
import td.info507.myppoker2.dialog.CardDialogFragment
import td.info507.myppoker2.request.CardRequest
import java.nio.file.Files.delete

class MainActivity : Updatable, AppCompatActivity() {
    companion object {
        const val EXTRA_CARD = "EXTRA_CARD"
    }

    private lateinit var list: RecyclerView
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.card_list)
        list.adapter = object: CardAdapter(applicationContext) {
            override fun onClickListener(view: View) {
                var intent = Intent(applicationContext, CardActivity::class.java).apply{
                    putExtra(EXTRA_CARD, view.tag as Int)
                }
                startActivity(intent)
            }

            override fun onLongClickListener(view: View): Boolean {
                CardStorage.get(applicationContext).delete(view.tag as Int)
                update()
                return true
            }
        }

        refresh = findViewById(R.id.card_refresh)
        refresh.setOnRefreshListener {
            CardRequest(applicationContext, this)
        }

        findViewById<FloatingActionButton>(R.id.add_card).setOnClickListener() {
            CardDialogFragment(this).show(supportFragmentManager, null)
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

    override fun update() {
        list.adapter?.notifyDataSetChanged()
        refresh.isRefreshing = false
    }
}