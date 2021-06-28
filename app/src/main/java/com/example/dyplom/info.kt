package com.example.dyplom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.menu.view.button

class info: Fragment()
{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v=inflater.inflate(R.layout.info,container,false)
        var fManager=activity!!.supportFragmentManager

        val myButton: ImageButton = v.findViewById(R.id.button) as ImageButton
        myButton.setOnClickListener {
            var tx =fManager.beginTransaction()
            tx.replace(R.id.frag,Menu())
            tx.addToBackStack(null)
            tx.commit()
        }
        return v
    }

}