package com.example.primeiroexercicio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_constraint_layout.*
import kotlinx.android.synthetic.main.activity_relative_layout.*

class RelativeLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_layout)

        relativeButton.setOnClickListener() {
            emptyInput()
        }
    }

    fun emptyInput() :Boolean {
        var valid = true
        if (relativeNameInput.text.toString().trim().isEmpty()) {
            relativeNameInput.error = "Obrigatório"
            valid = false
        }
        if (relativeAgeInput.text.toString().trim().isEmpty()) {
            relativeAgeInput.error = "Obrigatório"
            valid = false
        }
        return valid
    }
}