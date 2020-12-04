package com.example.week_6.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.week_6.R
import com.example.week_6.model.LocalImageGallery.localImageGallery
import com.example.week_6.model.RemoteImageGallery.remoteImageGallery
import com.example.week_6.model.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_local_gallery.*

class LocalGalleryActivity : AppCompatActivity() {

    private val localAdapter = ViewPagerAdapter(localImageGallery, this)
    private val remoteAdapter = ViewPagerAdapter(remoteImageGallery, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_gallery)

        tabConfig()
    }

    fun tabConfig(){
        val galleryExtra = intent.getStringExtra(GALLERY_TYPE)

        if (galleryExtra == LOCAL_GALLERY){
            viewPager.adapter = localAdapter
        } else if (galleryExtra == REMOTE_GALLERY){
            viewPager.adapter = remoteAdapter
        }

        TabLayoutMediator(tlLocalTabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> { tab.text = "Vertical"}
                1 -> { tab.text = "Horizontal"}
                2 -> { tab.text = "Grid"}
            }
        }.attach()

        tlLocalTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }
}