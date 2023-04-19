package com.example.rebrickable.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Minifig(
    val name: String,
    @SerialName("num_parts")
    val partsCount: Int,
    @SerialName("set_img_url")
    val imageURL: String?,
    @SerialName("set_url")
    val rebrickableURL: String?

)
