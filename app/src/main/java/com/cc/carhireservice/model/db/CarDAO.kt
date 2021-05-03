package com.cc.carhireservice.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cc.carhireservice.model.data.Car

@Dao
interface CarDAO {

    @Query ("SELECT * FROM Car")
    fun getAll() : List<Car>

    @Insert
    fun insertAll(vararg cars:Car)

    @Update (entity = Car::class)
    fun updateCar(vararg  cars: Car)

}