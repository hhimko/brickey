package com.example.rebrickable.models


data class PagedResponse<T>(
    val previous: String?,
    val next: String?,
    val results: Array<T>
) {
    val count: Int
        get() { return results.size }

}
