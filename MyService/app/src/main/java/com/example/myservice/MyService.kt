package com.example.myservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "Service Created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService", "Service Started")
        sendDataToPC("ksh_test")
        Thread {
            for (i in 1..5) {
                Log.d("MyService", "Counting: $i")
                Thread.sleep(1000)
            }
//            stopSelf()//종료 x
        }.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "Service Destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    fun sendDataToPC(message: String) {
        val client = OkHttpClient()
        val json = "{\"msg\":\"$message\"}"
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url("http://192.168.219.101:5000/data") // ★ PC 내부IP + 포트
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Service", "전송 실패", e)
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d("Service", "전송 성공: ${response.body?.string()}")
            }
        })
    }

}
