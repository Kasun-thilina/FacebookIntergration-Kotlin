package com.kasunthilina.ktfbintergration.Retrofit

data class DataModelDTO(var id:Int=0, var title:String, var description:String, var address:String, var postcode:String
                        , var phoneNumber:String, var latitude:String, var longitude:String,
                        var imageURL:List<ImageDTO>) {


}