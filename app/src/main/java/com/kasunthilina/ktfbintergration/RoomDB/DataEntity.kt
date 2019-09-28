package com.kasunthilina.ktfbintergration

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DataEntity {
    @PrimaryKey
    var colID:Int?=0
    @ColumnInfo(name="UserName")
    var userName:String?=" "
    @ColumnInfo(name="Image_URL")
    var imageUrl:String?=" "

}