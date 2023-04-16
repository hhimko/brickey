package com.example.rebrickable.models

import kotlinx.serialization.Serializable


@Serializable
data class PagedResponse<T>(
    val previous: String?,
    val next: String?,
    val results: List<T>
) {
    val count: Int
        get() { return results.size }
}
