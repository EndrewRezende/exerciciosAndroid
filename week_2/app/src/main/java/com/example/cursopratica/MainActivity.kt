package com.example.cursopratica

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

const val RESULT_MESSAGE_EXTRA_KEY = "Result_message"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirstNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                clearVerification()
            }
        })
        etSecondNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                clearVerification()
            }
        })

        btAdd.setOnClickListener{
            operation(getString(R.string.add_operation_code))
        }
        btSubtract.setOnClickListener{
            operation(getString(R.string.subtract_operation_code))
        }
        btMultiply.setOnClickListener{
            operation(getString(R.string.multiply_operation_code))
        }
        btDivide.setOnClickListener{
            operation(getString(R.string.divide_operation_code))
        }
    }

    private fun clearVerification() {
        val buttonEnabled = !(etFirstNumber.text.isEmpty() || etSecondNumber.text.isEmpty())
        btAdd.isEnabled = buttonEnabled
        btSubtract.isEnabled = buttonEnabled
        btMultiply.isEnabled = buttonEnabled
        btDivide.isEnabled = buttonEnabled
    }


    private fun inputVerification(): Boolean {
        var verification = true

        if (etFirstNumber.text.toString().trim().isEmpty()) {
            etFirstNumber.error = getString(R.string.invalid)
            verification = false
        }
        if (etSecondNumber.text.toString().trim().isEmpty()) {
            etSecondNumber.error = getString(R.string.invalid)
            verification = false
        }
        return verification
    }

    private fun operation (operationCode: String) {

        if (inputVerification()) {

            val firstInput = etFirstNumber.text.toString().trim().toBigDecimal()
            val secondInput = etSecondNumber.text.toString().trim().toBigDecimal()
            var result: BigDecimal = BigDecimal.ZERO

            when (operationCode) {

                getString(R.string.add_operation_code) -> {
                    result = firstInput.add(secondInput)
                }
                getString(R.string.subtract_operation_code) -> {
                    result = firstInput.subtract(secondInput)
                }
                getString(R.string.multiply_operation_code) -> {
                    result = firstInput.multiply(secondInput)
                }
                getString(R.string.divide_operation_code) -> {
                    if (secondInput.compareTo(BigDecimal.ZERO) == 0) {
                        etSecondNumber.error = getString(R.string.invalid)
                    } else {
                        result = firstInput.divide(secondInput, RoundingMode.HALF_UP)
                    }
                }
            }
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra(RESULT_MESSAGE_EXTRA_KEY, result.toString())
            }
            startActivity(intent)
            tvClear()
        }
    }
    private fun tvClear(){
        etFirstNumber.text.clear()
        etSecondNumber.text.clear()
    }
}