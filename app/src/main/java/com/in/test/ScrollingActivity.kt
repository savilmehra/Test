package com.`in`.test

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.`in`.test.entities.Test
import com.`in`.test.retrofit.ApiClient
import com.`in`.test.retrofit.Webservices
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_scrolling.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScrollingActivity : AppCompatActivity() {

    private var rvMonDay: RecyclerView? = null
    private var rvThus: RecyclerView? = null
    private var rvWed: RecyclerView? = null
    private var adapter1: AdapterDetails? = null
    private var adapter2: AdapterThursday? = null
    private var adapter3: WedNesdayAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        rvMonDay = findViewById(R.id.rvmonday)
        rvThus = findViewById(R.id.rvThus)
        rvWed = findViewById(R.id.rvWed)

        rvMonDay!!.layoutManager = GridLayoutManager(this@ScrollingActivity!!, 2)
        adapter1 = AdapterDetails(this@ScrollingActivity!!)
        rvMonDay!!.adapter = adapter1

        rvThus!!.layoutManager = GridLayoutManager(this@ScrollingActivity!!, 2)
        adapter2 = AdapterThursday(this@ScrollingActivity!!)
        rvThus!!.adapter = adapter2


        rvWed!!.layoutManager = GridLayoutManager(this@ScrollingActivity!!, 2)
        adapter3 = WedNesdayAdapter(this@ScrollingActivity!!)
        rvWed!!.adapter = adapter3
        getUpdateHistory()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun getUpdateHistory() {
        Log.e("updatehistory", "called")

        val apiService = ApiClient.builder().create(Webservices::class.java)
        val call = apiService.getData()
        call.enqueue(object : Callback<Test> {
            override fun onResponse(
                call: Call<Test>,
                response: Response<Test>
            ) {
                if (response != null && response.body() != null) {
                    val data = response.body() as Test
                    if (data != null) {
                        adapter2!!.setData(data.weekDietData.thursday)
                        adapter3!!.setData(data.weekDietData.wednesday)
                        adapter1!!.setData(data.weekDietData.monday)
                        Toast.makeText(
                            applicationContext,
                            " Success",
                            Toast.LENGTH_SHORT

                        ).show()

                    }

                } else {
                    Toast.makeText(applicationContext, "No Success", Toast.LENGTH_SHORT).show()
                }


            }

            override fun onFailure(call: Call<Test>, t: Throwable) {

                Toast.makeText(applicationContext, " Call Failed Try Again", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
}
