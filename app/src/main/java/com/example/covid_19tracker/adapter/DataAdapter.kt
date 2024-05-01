package com.example.covid_19tracker.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.covid_19tracker.R
import com.example.covid_19tracker.model.ModelClass

class DataAdapter(private val context : Context, private val list:ArrayList<ModelClass>):ArrayAdapter<ModelClass>(context,R.layout.testing,list){
    @SuppressLint("ViewHolder")

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): ModelClass {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.testing, parent, false)
        } else {
            view = convertView
        }

        val state : TextView = view.findViewById(R.id.cityName)
        val active : TextView = view.findViewById(R.id.active)
        val incActive : TextView = view.findViewById(R.id.incActive)
        val cured : TextView = view.findViewById(R.id.cured)
        val incCured : TextView = view.findViewById(R.id.incCured)
        val death : TextView = view.findViewById(R.id.death)
        val incDeath : TextView = view.findViewById(R.id.incDeath)
        val total : TextView = view.findViewById(R.id.total)


        //getting Data
        val curr = list[position]
        state.text = list[position].toString()
        state.text = curr.cityName
        active.text = curr.active
        incActive.text = curr.incAct
        cured.text = curr.cured
        death.text = curr.death
        incDeath.text = curr.incDec
        total.text = curr.total
        incCured.text = curr.incRec

        Log.d("State","State $state")

        return view
    }
}