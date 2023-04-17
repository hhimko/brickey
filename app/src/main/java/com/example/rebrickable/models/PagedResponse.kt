package com.example.rebrickable.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PagedResponse<T>(
    @SerialName("count")
    val resultsCount: Int,
    val previous: String?,
    val next: String?,
    val results: List<T>
)
