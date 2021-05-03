package com.cc.carhireservice.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity (tableName = "Car")
data class Car  (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id")
    var id:Int,

    @ColumnInfo (name = "name")
    var name:String,

    @ColumnInfo(name = "pricePerDay")
    var pricePerDay:Int,

    @ColumnInfo(name = "licenseTag")
    var licenseTag:String,

    @ColumnInfo (name = "available")
    var available:Boolean = false
) {
    //Car(name, pricePerDay, id, licenseTag, available(boolean))







}