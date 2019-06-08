package com.example.pocket

import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.pocket.memo.MemoFragment
import com.example.pocket.mine.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), RemindFragment.OnFragmentInteractionListener {

    private val fragmentList: ArrayList<Fragment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentList.add(MemoFragment())
        fragmentList.add(RemindFragment.newInstance())
        fragmentList.add(MineFragment())
        pager.adapter = MyFragmentPagerAdapter(supportFragmentManager, fragmentList)
        pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position : Int) {
                selectNav(position)
            }
        })

        btn_memory.setOnClickListener {
            pager.currentItem = 0
        }

        btn_tips.setOnClickListener {
            pager.currentItem = 1
        }

        btn_mine.setOnClickListener {
            pager.currentItem = 2
        }

        pager.currentItem = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun selectNav(index : Int) {
        when (index) {
            0 -> {
                btn_memory.img_memory.background = getDrawable(R.drawable.mem_selected)
                btn_mine.img_mine.background = getDrawable(R.drawable.mine_unselected)
                btn_tips.img_tips.background = getDrawable(R.drawable.note_unselected)
            }

            1 -> {
                btn_memory.img_memory.background = getDrawable(R.drawable.mem_unselected)
                btn_mine.img_mine.background = getDrawable(R.drawable.mine_unselected)
                btn_tips.img_tips.background = getDrawable(R.drawable.note_selected)
            }

            2 -> {
                btn_memory.img_memory.background = getDrawable(R.drawable.mem_unselected)
                btn_mine.img_mine.background = getDrawable(R.drawable.mine_selected)
                btn_tips.img_tips.background = getDrawable(R.drawable.note_unselected)
            }
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
