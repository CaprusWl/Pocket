package com.example.pocket.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class Date {


    @ColumnInfo(name = "year")
    public var year: Int = 2019

    @ColumnInfo(name = "month")
    public var month: Int = 6

    @ColumnInfo(name = "day")
    public var day: Int = 9


}