package com.example.rebrickable.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.net.URL


@Serializable
data class Set(
    @SerialName("set_num")
    val setNum: String,
    val name: String,
    @SerialName("year")
    val releaseYear: Int,
    @SerialName("theme_id")
    val themeId: Int,
    @SerialName("num_parts")
    val partCount: Int,
    @SerialName("set_img_url")
    val imageURL: String?,
    @SerialName("set_url")
    val rebrickableURL: String?

) {
    @Transient
    var theme: Theme? = null

}
