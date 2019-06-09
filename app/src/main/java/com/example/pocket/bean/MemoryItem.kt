package com.example.pocket.bean

import com.example.pocket.data.Date

data class MemoryItem(
    var date : Date,
    var fromID : String = "caprus",
    var title : String = "hackday",
    var videoNum : Int = 1,
    var photoNum : Int = 2,
    var audioNum : Int = 3
)