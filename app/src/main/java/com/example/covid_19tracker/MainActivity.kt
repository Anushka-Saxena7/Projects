package com.example.covid_19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid_19tracker.adapter.DataAdapter
import com.example.covid_19tracker.databinding.ActivityMainBinding
import com.example.covid_19tracker.model.ModelClass
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list : ArrayList<ModelClass>
    private lateinit var cityNameList: String
    private lateinit var dataAdapter : DataAdapter
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList<ModelClass>()
        listView = findViewById<ListView>(R.id.listView)
        cityNameList = arrayOf("Pryagraj","Agra","Ballia").toString()

        fetchData()

    }

    private fun fetchData() {

        val url = "https://data.covid19india.org/state_district_wise.json"
        val request = StringRequest(Request.Method.GET,url,
            {
                    response ->
                
                Log.d("Object"," ERROR : $response")
                val objectRequest = JSONObject(response)

                val object1 = objectRequest.getJSONObject("Uttar Pradesh")
                val object2 = object1.getJSONObject("districtData")
                var object3 = object2.getJSONObject("Prayagraj")
                var object4 = object3.getJSONObject("delta")

                val cityName = object1.getString("districtData")
                Log.d("CityName","city : $cityName")
                var active = object3.getString("active")
                var confirmed = object3.getString("confirmed")
                var deceased = object3.getString("deceased")
                var recovered = object3.getString("recovered")

                var confInc = object4.getString("confirmed")
                var confDec = object4.getString("deceased")
                var confRec = object4.getString("recovered")

                  val model = ModelClass("Prayagraj", confirmed, deceased, recovered, active, confInc, confDec, confRec)

                list.add(model)

                // Creating JSON Object
                object3 = object2.getJSONObject("Ballia")

                // From that object we are fetching data
                active = object3.getString("active")
                confirmed = object3.getString("confirmed")
                deceased = object3.getString("deceased")
                recovered = object3.getString("recovered")
                object4 = object3.getJSONObject("delta")
                confInc = object4.getString("confirmed")
                confDec = object4.getString("deceased")
                confRec = object4.getString("recovered")

                val model2 = ModelClass("Ballia", confirmed, deceased, recovered, active, confInc, confDec, confRec)
                //placing data into the app using AdapterClass
                 list.add(model2)

                object3 = object2.getJSONObject("Lucknow")

                active = object3.getString("active")
                confirmed = object3.getString("confirmed")
                deceased = object3.getString("deceased")
                recovered = object3.getString("recovered")
                object4 = object3.getJSONObject("delta")
                confInc = object4.getString("confirmed")
                confDec = object4.getString("deceased")
                confRec = object4.getString("recovered")

                val model3= ModelClass("Lucknow",confirmed,deceased,recovered,active,confInc,confDec,confRec)

                list.add(model3)

                object3 = object2.getJSONObject("Agra")

                active = object3.getString("active")
                confirmed = object3.getString("confirmed")
                deceased = object3.getString("deceased")
                recovered = object3.getString("recovered")
                object4 = object3.getJSONObject("delta")
                confInc = object4.getString("confirmed")
                confDec = object4.getString("deceased")
                confRec = object4.getString("recovered")

                val model4 = ModelClass("Agra",confirmed,deceased,recovered,active,confInc,confDec,confRec)

                list.add(model4)


                dataAdapter = DataAdapter(this,list)
                dataAdapter.notifyDataSetChanged()
                listView.adapter = dataAdapter

            },{
                    error->
                Toast.makeText(this,"$error",Toast.LENGTH_LONG).show()

            })
        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(request)
    }
}