package com.example.week_6.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.week_6.R
import kotlinx.android.synthetic.main.activity_main.*

const val LOCAL_GALLERY = "localGallery"
const val REMOTE_GALLERY = "remoteGallery"
const val GALLERY_TYPE = "galleryType"

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivUpperImage.setOnClickListener{
            activityStartExtra(LOCAL_GALLERY)
        }

        ivLowerImage.setOnClickListener {
            activityStartExtra(REMOTE_GALLERY)
        }
    }
    fun activityStartExtra(gallery: String){
        val intent = Intent(this, LocalGalleryActivity::class.java).apply {
            putExtra(GALLERY_TYPE, gallery)
        }
        startActivity(intent)
    }
}