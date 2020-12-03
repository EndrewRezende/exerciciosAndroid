package com.example.cursopratica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result_page.*


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        val result = intent.getStringExtra(RESULT_MESSAGE_EXTRA_KEY)

        tvResultView.text = result

        btResultBack.setOnClickListener {
            finish()
        }
    }
}

