package com.example.kuisioner_app

data class City (
    var capital: Boolean,
    var country: String? = null,
    var name: String? = null,
    var population: Int = 0
        ){
    constructor() : this(false, null, null, 0)
}