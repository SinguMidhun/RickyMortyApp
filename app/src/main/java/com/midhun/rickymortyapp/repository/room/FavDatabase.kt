package com.midhun.rickymortyapp.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.midhun.rickymortyapp.repository.LocalApi


@Database(entities = [FavCharactersTable::class], version = 1)
abstract class FavDatabase : RoomDatabase() {

    companion object{

        private var databaseInstance : FavDatabase? = null

        fun getDatabase(context : Context) : FavDatabase {
            if(databaseInstance ==null){
                synchronized(this){
                    databaseInstance = Room.databaseBuilder(context.applicationContext,
                        FavDatabase::class.java
                        ,"favtable")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return databaseInstance!!
        }
    }

    abstract fun getProductsDao() : LocalApi

}