package com.kasunthilina.ktfbintergration

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(DataEntity::class)],version = 1,exportSchema = false)
abstract class Database : RoomDatabase(){
    abstract fun dbDao():Dao

}