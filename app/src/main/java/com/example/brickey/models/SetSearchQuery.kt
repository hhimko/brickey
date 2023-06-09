package com.example.brickey.models

import kotlinx.serialization.Serializable


@Serializable
data class SetSearchQuery(
    var searchTerm: String,
    var themeId: Int?,
    var releaseYear: Int?
) : java.io.Serializable {
    constructor(searchTerm: String) : this(searchTerm, null, null)

}
