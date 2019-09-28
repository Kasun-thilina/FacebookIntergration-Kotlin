package com.kasunthilina.ktfbintergration.Retrofit

import com.google.gson.annotations.SerializedName

data class ListDTO (@SerializedName("data") var data:List<DataModelDTO>){
}