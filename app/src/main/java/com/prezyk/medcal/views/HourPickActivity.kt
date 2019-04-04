package com.prezyk.medcal.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.ListView
import android.widget.RelativeLayout
import com.prezyk.medcal.adapters.HoursAdapter
import com.prezyk.medcal.R
import kotlinx.android.synthetic.main.add_event_pick_hours.*
import java.util.*

class HourPickActivity : AppCompatActivity() {

    lateinit var selectedDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_event_pick_hours)

        selectedDate = Calendar.getInstance()

        selectedDate.timeInMillis = intent.extras.getLong("dateMillis")





        val hoursArr = this.getHoursArray(selectedDate)

        var hoursAdapter = HoursAdapter(this, hoursArr)

        hoursListView.adapter = hoursAdapter

        var pickedHours: Array<Calendar>

        //TODO finish activity, send picked hours to invoking activity
        acceptHoursBtn.setOnClickListener {
            pickedHours = this.getPickedHoursArray(hoursListView)

            for(i in 1 until pickedHours.size) {
                intent.extras.putLong("pickedHour$i", pickedHours[i].timeInMillis)
            }
            setResult(0)
            finish()
        }


    }


    fun getHoursArray(selectedDate: Calendar): Array<Calendar> {

        var hours = arrayOfNulls<Calendar>(24)

        selectedDate.set(
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH),
            0,0,0)
        for(i in 0..23) {
            selectedDate.add(Calendar.HOUR_OF_DAY, 1)
            hours[i] = selectedDate.clone() as Calendar
        }

        return hours.requireNoNulls()
    }

    //TODO get checked items from listview, line 70 fuckup
    fun getPickedHoursArray(listView: ListView) : Array<Calendar> {
        var hoursList = ArrayList<Calendar>()
        var tempRelativeLayout: RelativeLayout
        var tempCheckBox: CheckBox
//        for(i in 1 until listView.count) {
//            tempRelativeLayout = listView.getChildAt(i) as RelativeLayout
//            if(tempRelativeLayout.isChecked) {
//                hoursList.add(listView.getItemAtPosition(i) as Calendar)
//                Log.i("CHECKED ITEM", hoursList.last().toString() )
//            }
//        }

        return hoursList.toTypedArray()
    }
}