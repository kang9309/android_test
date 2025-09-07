package com.example.test

import android.util.Log
import fi.iki.elonen.NanoHTTPD

class SimpleWebServer : NanoHTTPD(8080) {
    override fun serve(session: IHTTPSession): Response {
        return when (session.uri) {
            "/start" -> {
                Log.d("MacroService", "매크로 시작 명령 받음")
                newFixedLengthResponse("Started")
            }
            "/stop" -> {
                Log.d("MacroService", "매크로 정지 명령 받음")
                newFixedLengthResponse("Stopped")
            }
            else -> newFixedLengthResponse("Unknown Command")
        }
    }
}