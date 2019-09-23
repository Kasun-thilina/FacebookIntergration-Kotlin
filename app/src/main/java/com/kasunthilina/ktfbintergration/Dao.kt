package com.kasunthilina.ktfbintergration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    fun saveData(dt:DataEntity)

    @Query("select * from DataEntity")
    fun getData():List<DataEntity>

}