package com.example.startproject

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectDateButton: Button = findViewById(R.id.btnSelectDate)
        selectDateButton.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                showTextViews()
                val selectedDateLabel: TextView = findViewById(R.id.tvSelectedDateLabel)
                selectedDateLabel.text = "$dayOfMonth.${month + 1}.$year"
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDateInMinutes = sdf.parse("$dayOfMonth/${month + 1}/$year").time / 60000
                val currentDateInMinutes =
                    sdf.parse(sdf.format(System.currentTimeMillis())).time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                val totalMinutesLabel: TextView = findViewById(R.id.tvTotalMinutesLabel)
                totalMinutesLabel.text = differenceInMinutes.toString()
            }, year, month, day
        ).show()
    }

    private fun showTextViews() {
        findViewById<TextView>(R.id.tvSelectedDateText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvTotalMinutesText).visibility = View.VISIBLE
    }
}