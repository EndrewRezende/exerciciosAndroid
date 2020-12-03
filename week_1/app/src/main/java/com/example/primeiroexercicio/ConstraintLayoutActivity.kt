package com.example.primeiroexercicio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_constraint_layout.*

class ConstraintLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)

        constraintButton.setOnClickListener() {
            emptyInput()
        }
    }

    fun emptyInput() :Boolean {
        var valid = true
        if (constraintNameInput.text.toString().trim().isEmpty()) {
            constraintNameInput.error = "Obrigatório"
            valid = false
        }
        if (constraintAgeInput.text.toString().trim().isEmpty()) {
            constraintAgeInput.error = "Obrigatório"
            valid = false
        }
        return valid
    }
}