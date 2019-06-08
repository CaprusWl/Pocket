package com.example.pocket.bean

import com.example.pocket.data.Date

data class MemoryItem(
    val date : Date,
    val fromID : String = "caprus",
    val title : String = "hackday",
    val videoNum : Int = 1,
    val photoNum : Int = 2,
    val audioNum : Int = 3
)