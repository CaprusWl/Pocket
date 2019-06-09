package com.example.pocket.mine


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pocket.MainActivity

import com.example.pocket.R

class MineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val isParent = (activity as MainActivity).isParent


        return if (isParent) {
            inflater.inflate(R.layout.fragment_mine, container, false)
        } else {
            inflater.inflate(R.layout.fragment_mine_child, container, false)
        }
    }



}
