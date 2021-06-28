package com.example.dyplom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dyplom.DataPoint
import com.example.dyplom.GraphView
import com.example.dyplom.R

class StatisticRecyclerAdapter(private val names: List<List<DataPoint>>) :
        RecyclerView.Adapter<StatisticRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var graph_view: GraphView? = null
        var text: TextView? = null

        init {
            text = itemView.findViewById(R.id.legent)
            graph_view = itemView.findViewById(R.id.graph_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.rec_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.graph_view?.setData(names[position])
        holder.text?.text = "Element $position"
    }

    override fun getItemCount() = names.size
}