package com.example.test

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.graphics.Path
import android.accessibilityservice.GestureDescription


class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event != null) {
            Log.d("MyAccessibilityService", "Event: ${event.eventType}, ${event.text}")
        }
    }

    override fun onInterrupt() {
        Log.d("MyAccessibilityService", "Service Interrupted")
    }

    fun clickAt(x: Int, y: Int) {
        val path = Path().apply { moveTo(x.toFloat(), y.toFloat()) }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 100))
            .build()
        dispatchGesture(gesture, null, null)
    }
}
