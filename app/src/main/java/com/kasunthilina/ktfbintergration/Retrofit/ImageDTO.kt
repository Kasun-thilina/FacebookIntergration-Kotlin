package com.kasunthilina.ktfbintergration.Retrofit

import com.google.gson.annotations.SerializedName

data class ImageDTO (@SerializedName("image") var small:String, var medium:String, var large:String){
}