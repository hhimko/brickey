package com.example.rebrickable.models

import com.example.rebrickable.RebrickableApiClient
import kotlinx.serialization.Serializable
import com.example.rebrickable.models.partial.ThemePartial


@Serializable
data class Theme(
    val id: Int,
    val name: String,
    val parent: Theme?
): java.io.Serializable {

    fun getFullThemeName(): String {
        return "LEGO®" + _getFullThemeNameBuilder(this)
    }


    companion object {

        private fun _getFullThemeNameBuilder(theme: Theme): String {
            if (theme.parent == null)
                return " ${theme.name}"

            return _getFullThemeNameBuilder(theme.parent) + ": ${theme.name}"
        }

        suspend fun fromPartial(client: RebrickableApiClient, partial: ThemePartial): Theme {
            val parent = if (partial.parentId != null) client.getThemeByIdAsync(partial.parentId) else null
            return Theme(partial.id, partial.name, parent)
        }
    }
}
