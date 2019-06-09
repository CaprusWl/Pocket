package com.example.pocket

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_enter.*

class EnterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.TRANSPARENT
        }
        val intent = Intent(this, MainActivity::class.java)
        im_child.setOnClickListener {
            intent.putExtra(MainActivity.CLIENT_TYPE, false)
            startActivity(intent)
            finish()
        }
        im_parent.setOnClickListener {
            intent.putExtra(MainActivity.CLIENT_TYPE, true)
            startActivity(intent)
            finish()
        }
    }
}
