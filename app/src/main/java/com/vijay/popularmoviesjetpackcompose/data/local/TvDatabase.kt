package com.vijay.popularmoviesjetpackcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vijay.popularmoviesjetpackcompose.data.TvEntity



@Database(entities = [TvEntity::class], version = 1)
abstract class TvDatabase : RoomDatabase() {

    abstract val dao :  TvDao
}