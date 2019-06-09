package com.example.pocket

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_remind_add.*
import java.text.SimpleDateFormat
import java.util.*

class RemindAddActivity : AppCompatActivity() {
    companion object {
        val SEND_TO: String = "send_to"
        val SEND_CONTENT: String = "send_content"
        val REMIND_TIME: String = "remind_time"
        val REPEAT_MODE: String = "repeat_mode"

        enum class RepeatFreq { NONE, EVERY_DAY, EVERY_WEEK, EVERY_TWO_WEEK, EVERY_MONTH, EVERY_YEAR }
    }

    var repeatFreq: RepeatFreq = Companion.RepeatFreq.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remind_add)
        repeat_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                repeat_select.visibility = View.VISIBLE
                setRepeatMode(Companion.RepeatFreq.EVERY_DAY)
            } else {
                repeat_select.visibility = View.GONE
            }
        }

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.TRANSPARENT
        }

        remind_time_text.text =
            SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA).format(System.currentTimeMillis() + 1000000)
        every_day.setOnClickListener { setRepeatMode(Companion.RepeatFreq.EVERY_DAY) }
        every_week.setOnClickListener { setRepeatMode(Companion.RepeatFreq.EVERY_WEEK) }
        every_two_week.setOnClickListener { setRepeatMode(Companion.RepeatFreq.EVERY_TWO_WEEK) }
        every_month.setOnClickListener { setRepeatMode(Companion.RepeatFreq.EVERY_MONTH) }
        every_year.setOnClickListener { setRepeatMode(Companion.RepeatFreq.EVERY_YEAR) }
        ensure_add_remind_button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(SEND_TO, send_to_edit_text.text.toString())
            intent.putExtra(SEND_CONTENT, send_content_edit_text.text.toString())
            intent.putExtra(REMIND_TIME, System.currentTimeMillis() + 1000000)
            intent.putExtra(REPEAT_MODE, repeatFreq)
            setResult(1, intent)
            finish()
        }
        remind_time_text.setOnClickListener {  }
    }

    fun setRepeatMode(repeatFreq: RepeatFreq) {
        every_day.typeface = Typeface.DEFAULT
        every_day.setTextColor(0x8A000000.toInt())
        every_week.typeface = Typeface.DEFAULT
        every_week.setTextColor(0x8A000000.toInt())
        every_two_week.typeface = Typeface.DEFAULT
        every_two_week.setTextColor(0x8A000000.toInt())
        every_month.typeface = Typeface.DEFAULT
        every_month.setTextColor(0x8A000000.toInt())
        every_year.typeface = Typeface.DEFAULT
        every_year.setTextColor(0x8A000000.toInt())
        when (repeatFreq) {
            Companion.RepeatFreq.NONE -> {
                this.repeatFreq = Companion.RepeatFreq.NONE
            }
            Companion.RepeatFreq.EVERY_DAY -> {
                this.repeatFreq = Companion.RepeatFreq.EVERY_DAY
                every_day.setTypeface(null, BOLD)
                every_day.setTextColor(0xFF9D7CF4.toInt())
            }
            Companion.RepeatFreq.EVERY_WEEK -> {
                this.repeatFreq = Companion.RepeatFreq.EVERY_WEEK
                every_week.setTypeface(null, BOLD)
                every_week.setTextColor(0xFF9D7CF4.toInt())
            }
            Companion.RepeatFreq.EVERY_TWO_WEEK -> {
                this.repeatFreq = Companion.RepeatFreq.EVERY_TWO_WEEK
                every_two_week.setTypeface(null, BOLD)
                every_two_week.setTextColor(0xFF9D7CF4.toInt())
            }
            Companion.RepeatFreq.EVERY_MONTH -> {
                this.repeatFreq = Companion.RepeatFreq.EVERY_MONTH
                every_month.setTypeface(null, BOLD)
                every_month.setTextColor(0xFF9D7CF4.toInt())
            }
            Companion.RepeatFreq.EVERY_YEAR -> {
                this.repeatFreq = Companion.RepeatFreq.EVERY_YEAR
                every_year.setTypeface(null, BOLD)
                every_year.setTextColor(0xFF9D7CF4.toInt())
            }
        }
    }
}
