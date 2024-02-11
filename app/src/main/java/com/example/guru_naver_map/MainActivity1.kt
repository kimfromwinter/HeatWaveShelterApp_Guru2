package com.example.guru_naver_map

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        val buttonStartNavigation = findViewById<Button>(R.id.button)

        // 경로 안내 시작 버튼을 누르면 Location 액티비티로 이동
        buttonStartNavigation.setOnClickListener {
            val intent = Intent(this, Location::class.java)
            startActivity(intent)
        }
    }
}
