package com.kuneosu.newcompose.util

import android.app.Activity
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

// 뒤로가기 버튼 2회 클릭 시 종료
// 사용 기술/지식 : Handler.postDelayed, OnBackPressedCallBack, handleOnBackPressed
@Suppress("DEPRECATION")
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