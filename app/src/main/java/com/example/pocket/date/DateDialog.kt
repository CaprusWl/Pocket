package com.example.pocket.date

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pocket.R
import com.savvi.rangedatepicker.CalendarPickerView
import java.text.SimpleDateFormat
import java.util.*

class DateDialog(context: Context) : Dialog(context, R.style.MyDialog) {

    public lateinit var dateList: List<Date>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val calendarView = View.inflate(context, R.layout.date_picker, null)
        val calendar = calendarView.findViewById<CalendarPickerView>(R.id.calendar_view)
        val button = calendarView.findViewById<Button>(R.id.dialog_button)
        button.setOnClickListener {
            dateList = calendar.selectedDates
            dismiss()
        }

        setCancelable(false)

        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 10)

        val lastYear = Calendar.getInstance()
        lastYear.add(Calendar.YEAR, -10)

        calendar.init(
            lastYear.time, nextYear.time,
            SimpleDateFormat("MM月", Locale.CHINA)
        )
            .inMode(CalendarPickerView.SelectionMode.RANGE)

        calendar.scrollToDate(Date())

        setContentView(calendarView)
    }
}