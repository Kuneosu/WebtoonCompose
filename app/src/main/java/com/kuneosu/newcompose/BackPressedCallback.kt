package com.kuneosu.newcompose

import android.app.Activity
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

class BackPressedCallBack(private var activity: Activity) {
    private var isDouble = false

    fun backPressToast() {
        Toast.makeText(activity, "종료하시려면 뒤로가기를 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
    }

    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when {
                isDouble -> {
                    activity.finish()
                }
            }
            backPressToast()

            isDouble = true
            Handler().postDelayed({
                isDouble = false
            }, 2000)

            Log.d("ONBACK", "handleOnBackPressed: ")
        }
    }
}