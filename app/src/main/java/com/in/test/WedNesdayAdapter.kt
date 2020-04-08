package com.`in`.test

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.`in`.test.R
import com.`in`.test.databinding.DetailsBinding
import com.`in`.test.entities.Monday
import com.`in`.test.entities.Wednesday

import org.greenrobot.eventbus.EventBus
import java.util.*


public class WedNesdayAdapter(private val context: Context) :
    RecyclerView.Adapter<WedNesdayAdapter.ItemHolder>() {
    private var list: List<Wednesday>? = null


    internal var activity: Activity? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WedNesdayAdapter.ItemHolder {

        val binding = DataBindingUtil.inflate<DetailsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.details,
            parent,
            false
        )
        return WedNesdayAdapter.ItemHolder(binding)

    }


    override fun onBindViewHolder(holder: WedNesdayAdapter.ItemHolder, position: Int) {
        val item = list!![position]

        if (item.mealTime != null)
            holder.binding.title.text = item.mealTime

        if (item.food != null)
            holder.binding.details.text = item.food
        val timme = item.mealTime
        val time = timme.split(":").toTypedArray()
        val hour = time[0].trim { it <= ' ' }.toInt()
        val min = time[1].trim { it <= ' ' }.toInt()
        start(hour, min,position)

    }

    private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        } else
            return true
    }

    override fun getItemCount(): Int {
        return if (list != null)
            list!!.size
        else
            0
    }

    fun setData(lists: List<Wednesday>?) {
        if (lists != null && lists.size > 0) {
            this.list = lists
            notifyDataSetChanged()
        }


    }

    class ItemHolder(val binding: DetailsBinding) : RecyclerView.ViewHolder(binding.root)

        fun start(hour: Int, min: Int,position:Int) {

        val alarmIntent = Intent(
            context,
            NotificationReceiver::class.java
        )
            val r =  Random()
            val i1 = r.nextInt() + 65
        val pendingIntent = PendingIntent.getBroadcast(context, i1, alarmIntent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.SECOND, 0)
            set(Calendar.DAY_OF_WEEK, 4)
        }

            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,pendingIntent)

    }
}
