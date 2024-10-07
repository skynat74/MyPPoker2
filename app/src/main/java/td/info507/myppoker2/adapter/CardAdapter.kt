package td.info507.myppoker2.adapter

import CardStorage
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.myppoker2.R

abstract class CardAdapter(private val context: Context): RecyclerView.Adapter<CardAdapter.CardHolder>() {
    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: LinearLayout = itemView.findViewById(R.id.card_layout)
        val value: TextView = itemView.findViewById(R.id.card_value)
        val description: TextView = itemView.findViewById(R.id.card_description)
    }

    abstract fun onClickListener(view: View)
    abstract fun onLongClickListener(view: View): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        view.setOnClickListener() {
            onClickListener(view)
        }
        view.setOnLongClickListener() {
            onLongClickListener(view)
        }
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val card = CardStorage.get(context).findAll()[position]
        holder.itemView.tag = card.id
        holder.layout.setBackgroundColor(Color.parseColor(card.color))
        holder.value.text = card.value
        holder.description.text = card.description
    }

    override fun getItemCount(): Int {
        return CardStorage.get(context).size()
    }
}