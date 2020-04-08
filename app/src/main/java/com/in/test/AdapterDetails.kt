package com.`in`.test

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.`in`.test.databinding.DetailsBinding
import com.`in`.test.entities.Monday
import java.util.*


public class AdapterDetails(private val context: Context) :
    RecyclerView.Adapter<AdapterDetails.ItemHolder>() {
    private var list: List<Monday>? = null


    internal var activity: Activity? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDetails.ItemHolder {

        val binding = DataBindingUtil.inflate<DetailsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.details,
            parent,
            false
        )
        return AdapterDetails.ItemHolder(binding)

    }


    override fun onBindViewHolder(holder: AdapterDetails.ItemHolder, position: Int) {
        val item = list!![position]

        if (item.mealTime != null)
            holder.binding.title.text = item.mealTime

        if (item.food != null)
            holder.binding.details.text = item.food
        val timme = item.mealTime
        val time = timme.split(":").toTypedArray()
        val hour = time[0].trim { it <= ' ' }.toInt()
        val min = time[1].trim { it <= ' ' }.toInt()
        start(hour, min, position)
    }


    override fun getItemCount(): Int {
        return if (list != null)
            list!!.size
        else
            0
    }

    fun setData(lists: List<Monday>?) {
        if (lists != null && lists.size > 0) {
            this.list = lists
            notifyDataSetChanged()
        }


    }

    class ItemHolder(val binding: DetailsBinding) : RecyclerView.ViewHolder(binding.root)

    fun start(hour: Int, min: Int, position: Int) {

        val alarmIntent = Intent(
            context,
            NotificationReceiver::class.java
        )
        val r = Random()
        val i1 = r.nextInt() + 65
        val pendingIntent = PendingIntent.getBroadcast(context, i1, alarmIntent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.DAY_OF_WEEK, 2)
        }

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

    }
}
