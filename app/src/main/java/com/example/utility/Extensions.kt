package com.example.utility

import android.widget.EditText


fun EditText.setOnSubmitListener(callback: () -> Unit) {
    setOnEditorActionListener { _, _, _ ->
        callback.invoke()
        return@setOnEditorActionListener true
    }
}
