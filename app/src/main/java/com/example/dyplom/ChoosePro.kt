package com.example.dyplom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dyplom.adapters.DBRecyclerAdapter
import com.example.dyplom.db.DataManager
import com.example.dyplom.db.Module
import kotlinx.android.synthetic.main.choose_pro.view.*

class ChoosePro: Fragment()
{
    // Inicjalizacja bazy danych
    private lateinit var dataManager : DataManager
    // Inicjalizacja listów
    var myList: MutableList<Module> = mutableListOf<Module>()
    var myString: MutableList<String> = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Wybór ekranu
        var v=inflater.inflate(R.layout.choose_pro,container,false)
        // Otwieranie bazy danych
        dataManager = DataManager(getContext()!!)
        // Inicjalizacja recyclerview
        val recyclerView: RecyclerView = v.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val sharedPreference =  getContext()!!.getSharedPreferences("programName",Context.MODE_PRIVATE)
        var stringName = sharedPreference.getString("programName","")
        // Sprawdzenie czy jest wartość wybrana
        if(!stringName.equals(""))
            // Pobieranie rekordu z bazy danych
            myList.add(dataManager.module(stringName!!)!!)
        // Dodanie informacji recyclerview
        recyclerView.adapter = DBRecyclerAdapter(myList)
        // Pobieranie wszystkich rekordów
        var items = dataManager.allModules()
        for(item in items){
            // Dodanie rekordów do listy
            myString.add(item.Name)
        }
        // Inicjalizacja adaptera dla autocompletetextview
        val adapter = ArrayAdapter(getContext()!!,
            android.R.layout.simple_list_item_1, myString)
        var textView = v.findViewById(R.id.autoTextView) as AutoCompleteTextView
        // Dodanie adaptera do autocompletetextview
        textView.setAdapter(adapter)
        // Dodanie obslugi klikniencia
        setListeners(v)
        return v
    }

    private fun setListeners(v: View ){
        var fManager=activity!!.supportFragmentManager
        v.buttonSearch.setOnClickListener {
            var item = dataManager.module(v.autoTextView.text.toString())
            val sharedPreference =  getContext()!!.getSharedPreferences("programName", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("programName", item?.Name)
            editor.commit()
            var tx =fManager.beginTransaction()
            tx.replace(R.id.frag,ChoosePro())
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