package com.example.primeiroexercicio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_constraint_layout.*
import kotlinx.android.synthetic.main.activity_linear_layout.*

class LinearLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout)


        linearButton.setOnClickListener() {
            emptyInput()
        }
    }

    fun emptyInput() :Boolean {
        var valid = true
        if (linearNameInput.text.toString().trim().isEmpty()) {
            linearNameInput.error = "Obrigatório"
            valid = false
        }
        if (linearAgeInput.text.toString().trim().isEmpty()) {
            linearAgeInput.error = "Obrigatório"
            valid = false
        }
        return valid
    }
}