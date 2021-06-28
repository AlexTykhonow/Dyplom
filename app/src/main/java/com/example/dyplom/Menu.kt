package com.example.dyplom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.menu.view.*

class Menu: Fragment()
{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Wybór ekranu
        var v=inflater.inflate(R.layout.menu,container,false)
        var fManager=activity!!.supportFragmentManager
        // Dodanie obslugi klikniencia
        v.button.setOnClickListener({
            // Otwieranie Sharepreference programName
            val sharedPreference =  getContext()!!.getSharedPreferences("programName", Context.MODE_PRIVATE)
            // Dodanie opcji edytowania
            var editor = sharedPreference.edit()
            // Wyzerowanie wartości
            editor.putString("programName", "")
            // Wykonanie
            editor.commit()
            // Dodanie funkcji otwarcia fragmentu Choose program
            var tx =fManager.beginTransaction()
            tx.replace(R.id.frag,ChoosePro())
            tx.addToBackStack(null)
            tx.commit()
        })
        v.button1.setOnClickListener({
            // Otwieranie Sharepreference requirements
            val sharedPreference =  getContext()!!.getSharedPreferences("requirements", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("clock", "")
            editor.putString("RAM", "")
            editor.commit()
            // Dodanie funkcji otwarcia fragmentu Choose requirement
            var tx =fManager.beginTransaction()
            tx.replace(R.id.frag,ChooseReq())
            tx.addToBackStack(null)
            tx.commit()
        })
        v.button2.setOnClickListener({
            // Dodanie funkcji otwarcia fragmentu informacja
            var tx =fManager.beginTransaction()
            tx.replace(R.id.frag,info())
            tx.addToBackStack(null)
            tx.commit()
        })
        return v
    }
}