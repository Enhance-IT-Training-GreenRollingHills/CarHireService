package com.cc.carhireservice.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cc.carhireservice.model.data.Car

@Database (entities = [Car::class], version = 1, exportSchema = false)
public abstract class CarHireServiceDatabase : RoomDatabase() {

    abstract fun carDao():CarDAO

    companion object {

        @Volatile
        private var INSTANCE: CarHireServiceDatabase? = null

        fun getInstance(context: Context): CarHireServiceDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CarHireServiceDatabase::class.java,
                        "car_hire_service_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

    /*companion object {

        @Volatile
        private var INSTANCE: CarHireServiceDatabase? = null

        fun getInstance(context: Context) : CarHireServiceDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarHireServiceDatabase::class.java,
                    "car_hire_service_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }*/

}