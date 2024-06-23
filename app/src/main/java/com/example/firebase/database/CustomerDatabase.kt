package com.example.firebase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CustomerEntity::class], version = 1)
abstract class CustomerDatabase: RoomDatabase(){

    abstract fun customerDao(): CustomerDAO

    companion object{
        val DATABASE_NAME: String = "sales"
        @Volatile
        private var INSTANCE: CustomerDatabase? = null
        fun getInstance(context: Context): CustomerDatabase? {
            INSTANCE?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}