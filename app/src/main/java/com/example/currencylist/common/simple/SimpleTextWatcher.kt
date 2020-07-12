package com.example.currencylist.common.simple

import android.text.Editable
import android.text.TextWatcher

class SimpleTextWatcher(
    private val beforeAction: (String) -> Unit
) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        if (s != null) beforeAction(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}