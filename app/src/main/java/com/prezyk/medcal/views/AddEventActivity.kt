package com.prezyk.medcal.views

import android.app.DatePickerDialog
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.prezyk.medcal.adapters.DrugAdapter
import com.prezyk.medcal.R
import com.prezyk.medcal.adapters.RecycleDrugAdapter
import com.prezyk.medcal.adapters.RecyclerEventsAdapter
import com.prezyk.medcal.presenters.AddEventPresenter
import kotlinx.android.synthetic.main.add_event_layout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddEventActivity : AppCompatActivity(), AddEventPresenter.View {
    //TODO ograniczenia na daty (data końcowa nie może być mniejsza niż początkowa)
    //TODO textfield/lista na zaznaczone godziny
    //TODO odnawianie zaznaczenia po ponownym włączeniu HourPickActivity
    //TODO wpieprzanie wszystkiego do bazy danych - tu będzie jazda
    //TODO kiedy radio na jednorazowe to data końcowa jest nieklikalna
    //TODO w HourPickActivity przycisk nie ma tekstu, a tytuł jest mały i może tego recyclera jakoś do środka wyrównać





    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var presenter: AddEventPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_event_layout)

        this.presenter = AddEventPresenter(this)

        presenter.setSelectedDate(intent.extras.getLong("dateMillis"))


        selectHoursBtn.setOnClickListener {
            presenter.onSubmitButtonClick()
        }

        everydayRadBtn.id = presenter.NONE
        onceRadBtn.id = presenter.ONCE
        weeklyRadBtn.id = presenter.WEEKLY

        periodicOptionsRadGrp.setOnCheckedChangeListener { _, checkedId -> presenter.updatePeriodicSelection(checkedId) }

        eventStartDateText.setOnClickListener() {
            presenter.onStartDateTextViewClick()
        }


        eventEndDateText.setOnClickListener {
            presenter.onEndDateTextViewClick()
        }


        viewManager = LinearLayoutManager(this)
        viewAdapter = RecycleDrugAdapter(presenter.drugList)


        recyclerView = findViewById<RecyclerView>(R.id.drugRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }

    override fun setStartDateTextView(text: String) {
        eventStartDateText.text = text
    }

    override fun setEndDateTextView(text: String) {
        eventEndDateText.text = text
    }

    override fun navigateToPickHours(date: Long) {
        val intent = Intent(this, HourPickActivity::class.java).apply {
            putExtra("dateMillis", date)
        }
        startActivityForResult(intent, 0)
    }

    override fun showStartDatePicker(sYear: Int, sMonth: Int, sDay: Int) {
        var datePickerDialog = DatePickerDialog(this ,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                presenter.updateSelectedStartDate(year, month, dayOfMonth)

            }, sYear, sMonth, sDay)

        datePickerDialog.show()
    }

    override fun showEndDatePicker(sYear: Int, sMonth: Int, sDay: Int) {
        var datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                presenter.updateSelectedEndDate(year, month, dayOfMonth)

            }, sYear, sMonth, sDay
        )

        datePickerDialog.show()
    }

    override fun submit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==0) {

            var hoursList = data?.extras?.getSerializable(HourPickActivity.PICKED_HOURS) as ArrayList<Calendar>
            presenter.updateHoursPicked(hoursList)
            var format = SimpleDateFormat("HH:mm")

            for(c: Calendar in hoursList) {
                Log.e("NO ERROR", format.format(c.time).toString())
            }
        }
    }
}