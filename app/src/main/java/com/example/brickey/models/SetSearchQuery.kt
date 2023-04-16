package com.example.brickey.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SetSearchQuery(
    var searchTerm: String,
    var themeId: Int?
) : Parcelable {
    constructor(searchTerm: String) : this(searchTerm, null)
}
