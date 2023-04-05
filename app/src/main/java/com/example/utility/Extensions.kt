package com.example.utility

import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView


fun EditText.setOnSubmitListener(callback: () -> Unit) {
    setOnEditorActionListener(object: TextView.OnEditorActionListener {
        override fun onEditorAction(obj: TextView?, ime_action_id: Int, e: KeyEvent?): Boolean {
            if (ime_action_id == EditorInfo.IME_ACTION_DONE) {
                callback.invoke()
                return true
            }
            return false
        }
    })
}
