package com.example.dyplom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dyplom.R
import com.example.dyplom.db.Module
// Inicjalizacja adaptera dla recyclerview
class DBRecyclerAdapter(private val names: List<Module>) :
        RecyclerView.Adapter<DBRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Inicjalizacja widoków
        var text1: TextView? = null
        var text2: TextView? = null
        var text3: TextView? = null
        var text4: TextView? = null

        init {
            text1 = itemView.findViewById(R.id.view1)
            text2 = itemView.findViewById(R.id.view2)
            text3 = itemView.findViewById(R.id.view3)
            text4 = itemView.findViewById(R.id.view4)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Wybór ekranu
        val itemView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.db_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Przypisanie wartości do widoków
        holder.text4?.text = names[position].RAM.toString()
        holder.text3?.text = names[position].Clock.toString()
        holder.text2?.text = names[position].Name
        holder.text1?.text = "$position-1"
    }
    // Funkcja do dostanie ilości rekordów
    override fun getItemCount() = names.size
}