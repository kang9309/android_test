package com.example.myservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "MainActivity onCreate")  // ← 여기서 로그 찍음


        // 서비스 바로 실행
        startService(Intent(this, MyService::class.java))
        // 액티비티는 바로 종료
        finish()
    }
}
