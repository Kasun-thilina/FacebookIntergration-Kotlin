package com.kasunthilina.ktfbintergration.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataModelDTO(var id:Int=0, var title:String, var description:String, var address:String, var postcode:String
                        , var phoneNumber:String, var latitude:String, var longitude:String,
                        @SerializedName("image") var image:ImageDTO) {




}