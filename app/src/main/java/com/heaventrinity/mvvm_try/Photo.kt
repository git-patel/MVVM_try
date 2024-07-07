package com.heaventrinity.mvvm_try

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
    var isSelected : Boolean = false
)
