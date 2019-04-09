package com.prezyk.medcal.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.CheckBox
import android.widget.ListView
import android.widget.RelativeLayout
import com.prezyk.medcal.adapters.HoursAdapter
import com.prezyk.medcal.R
import com.prezyk.medcal.adapters.RecyclerHoursAdapter
import com.prezyk.medcal.presenters.HourPickPresenter
import kotlinx.android.synthetic.main.add_event_pick_hours.*
import java.util.*

class HourPickActivity : AppCompatActivity(), HourPickPresenter.View {

    var presenter = HourPickPresenter(this)
    val PICKED_HOURS = "PICKED_HOURS"

    companion object {
        val PICKED_HOURS = "PICKED_HOURS"
    }

    override fun submit(hours: ArrayList<Calendar>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_event_pick_hours)

        var tempDate = Calendar.getInstance()
        tempDate.timeInMillis = intent.extras.getLong("dateMillis")
        presenter.updateSelectedDate(tempDate)


        var viewManager = LinearLayoutManager(this)
        var viewAdapter = RecyclerHoursAdapter(presenter.getHoursArray())

        var recyclerView = findViewById<RecyclerView>(R.id.hoursListView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


        //TODO finish activity, send picked hours to invoking activity
        acceptHoursBtn.setOnClickListener {
            var newIntent = Intent()
            newIntent.putExtra(PICKED_HOURS, presenter.onButtonSubmitClick())
            setResult(0, newIntent)
            finish()
        }


    }


//    fun getHoursArray(selectedDate: Calendar): Array<Calendar> {
//
//        var hours = arrayOfNulls<Calendar>(24)
//
//        selectedDate.set(
//            selectedDate.get(Calendar.YEAR),
//            selectedDate.get(Calendar.MONTH),
//            selectedDate.get(Calendar.DAY_OF_MONTH),
//            0,0,0)
//        for(i in 0..23) {
//            selectedDate.add(Calendar.HOUR_OF_DAY, 1)
//            hours[i] = selectedDate.clone() as Calendar
//        }
//
//        return hours.requireNoNulls()
//    }
//
//    //TODO get checked items from listview, line 70 fuckup
//    fun getPickedHoursArray(listView: ListView) : Array<Calendar> {
//        var hoursList = ArrayList<Calendar>()
//        var tempRelativeLayout: RelativeLayout
//        var tempCheckBox: CheckBox
////        for(i in 1 until listView.count) {
////            tempRelativeLayout = listView.getChildAt(i) as RelativeLayout
////            if(tempRelativeLayout.isChecked) {
////                hoursList.add(listView.getItemAtPosition(i) as Calendar)
////                Log.i("CHECKED ITEM", hoursList.last().toString() )
////            }
////        }
//
//        return hoursList.toTypedArray()
//    }
}