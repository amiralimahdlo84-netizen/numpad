package com.example.numpad

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button

class MyKeyboard : InputMethodService() {

    override fun onCreateInputView(): View {
        val view = layoutInflater.inflate(R.layout.keyboard_view, null)

        val map = mapOf(
            R.id.k1 to "۱", R.id.k2 to "۲", R.id.k3 to "۳",
            R.id.k4 to "۴", R.id.k5 to "۵", R.id.k6 to "۶",
            R.id.k7 to "۷", R.id.k8 to "۸", R.id.k9 to "۹",
            R.id.k0 to "۰"
        )

        map.forEach { (id, text) ->
            view.findViewById<Button>(id).setOnClickListener {
                val ic = currentInputConnection ?: return@setOnClickListener
                ic.commitText(text, 1)
            }
        }

        view.findViewById<Button>(R.id.kSpace).setOnClickListener {
            currentInputConnection?.commitText(" ", 1)
        }

        view.findViewById<Button>(R.id.kBack).setOnClickListener {
            val ic = currentInputConnection ?: return@setOnClickListener
            val selected = ic.getSelectedText(0)
            if (selected.isNullOrEmpty()) {
                ic.deleteSurroundingText(1, 0)
            } else {
                ic.commitText("", 1)
            }
        }

        return view
    }
}
