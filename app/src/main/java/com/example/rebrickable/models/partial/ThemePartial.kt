package com.example.rebrickable.models.partial

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ThemePartial(
    val id: Int,
    @SerialName("parent_id")
    val parentId: Int?,
    val name: String

)
