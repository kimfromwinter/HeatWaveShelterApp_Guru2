package com.example.guru_naver_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val buttonStartNavigation = findViewById<Button>(R.id.button)

        // 경로 안내 시작 버튼을 누르면 MainActivity로 돌아감.
        buttonStartNavigation.setOnClickListener {
            finish()
        }
    }
}