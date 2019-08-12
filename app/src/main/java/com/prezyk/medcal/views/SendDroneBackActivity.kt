package com.prezyk.medcal.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prezyk.medcal.R
import com.prezyk.medcal.presenters.SendDroneBackPresenter
import kotlinx.android.synthetic.main.drone_reached_destination.*

class SendDroneBackActivity(): AppCompatActivity(), SendDroneBackPresenter.View {

    lateinit var presenter: SendDroneBackPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drone_reached_destination)

        this.presenter = SendDroneBackPresenter(this)
        this.presenter.sendRequest(intent.extras.getInt("selectedOption"))

        sendDroneBackButton.setOnClickListener {
            this.presenter.sendDroneBack()
        }

    }

    override fun finishWithError() {
        var t = Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT)
        t.show()
        finish()
    }

    override fun finishWithMessage() {
        var t = Toast.makeText(this, "DRONE SUCCESSULLY REACHED DESTINATION", Toast.LENGTH_SHORT)
        t.show()
        var newIntent = Intent(this, MainActivity::class.java)
        startActivity(newIntent)
    }

    override fun showProgressBar() {
        dronOnWayTextView.visibility = View.VISIBLE
        droneOnWayProgressBar.visibility = View.VISIBLE
        droneReachedTextView.visibility = View.INVISIBLE
        sendDroneBackButton.visibility = View.INVISIBLE
    }

    override fun hideProgessBar() {
        dronOnWayTextView.visibility = View.INVISIBLE
        droneOnWayProgressBar.visibility = View.INVISIBLE
        droneReachedTextView.visibility = View.VISIBLE
        sendDroneBackButton.visibility = View.VISIBLE
    }
}