package com.example.dyplom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dyplom.adapters.DBRecyclerAdapter
import com.example.dyplom.db.DataManager
import com.example.dyplom.db.Module
import kotlinx.android.synthetic.main.choose_req.view.*
import kotlinx.android.synthetic.main.menu.view.button

class ChooseReq: Fragment()
{
    private lateinit var dataManager : DataManager
    var myList: List<Module> = mutableListOf<Module>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v=inflater.inflate(R.layout.choose_req,container,false)

        dataManager = DataManager(getContext()!!)

        val recyclerView: RecyclerView = v.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val sharedPreference =  getContext()!!.getSharedPreferences("requirements", Context.MODE_PRIVATE)
        var stringClock = sharedPreference.getString("clock","")
        var stringRAM = sharedPreference.getString("RAM","")
        if(!stringClock.equals("") && !stringRAM.equals(""))
            myList = dataManager.reqModules(stringClock!!.toDouble(),stringRAM!!.toInt())

        recyclerView.adapter = DBRecyclerAdapter(myList)
        setListeners(v)
        return v
    }

    private fun setListeners(v: View ){
        var fManager=activity!!.supportFragmentManager
        v.buttonSearch.setOnClickListener {
            val sharedPreference =  getContext()!!.getSharedPreferences("requirements", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("clock", v.EditText.text.toString())
            editor.putString("RAM", v.EditText2.text.toString())
            editor.commit()
            var tx =fManager.beginTransaction()
            tx.replace(R.id.frag,ChooseReq())
            tx.addToBackStack(null)
            tx.commit()
        }
        val myButton: ImageButton = v.findViewById(R.id.button) as ImageButton
        myButton.setOnClickListener {
            var tx =fManager.beginTransaction()
            tx.replace(R.id.frag,Menu())
            tx.addToBackStack(null)
            tx.commit()
        }
    }

}
/*{
    private lateinit var dataManager: DataManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v=inflater.inflate(R.layout.skybluefrag,container,false)
        var fManager=activity!!.supportFragmentManager
        val recyclerView: RecyclerView = v.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        dataManager = DataManager(context!!)

        recyclerView.adapter = StatisticRecyclerAdapter(newfulllist())

        v.button.setOnClickListener({
            var tx =fManager.beginTransaction()
            tx.replace(R.id.frag,Smile())
            tx.addToBackStack(null)
            tx.commit()
        })
        return v
    }
    private fun newfulllist(): List<List<DataPoint>>{
        val list = arrayListOf<List<DataPoint>>()
        var item = fillList()
        list.add(item)
        return list
    }
    private fun fillList(): List<DataPoint> {
        val random = Random()

        return (0..12).map {
            DataPoint(it, random.nextInt(100) + 1)
        }
    }


}*/