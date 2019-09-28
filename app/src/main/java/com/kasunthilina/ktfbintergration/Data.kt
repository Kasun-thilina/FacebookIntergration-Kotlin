package com.kasunthilina.ktfbintergration

class Data {

    private var title: String? = null
    private var description: String? = null
    private var address: String? = null
    private var postcode: String? = null
    private var phoneNumber: String? = null
    private var latitude: String? = null
    private var longitude: String? = null
    private var image: String? = null

    fun Data() {

    }


    fun getTitle(): String? {
        return title
    }

    fun getDescription(): String? {
        return description
    }

    fun getAddress(): String? {
        return address
    }

    fun getPostcode(): String? {
        return postcode
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun getLatitude(): String? {
        return latitude
    }

    fun getLongitude(): String? {
        return longitude
    }

    fun getImage(): String? {
        return image
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun setPostcode(postcode: String) {
        this.postcode = postcode
    }

    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }

    fun setLatitude(latitude: String) {
        this.latitude = latitude
    }

    fun setLongitude(longitude: String) {
        this.longitude = longitude
    }

    fun setImage(image: String) {
        this.image = image
    }
}