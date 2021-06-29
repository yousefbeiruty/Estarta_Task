package com.it.yousefl.mstarttask.data.remote.errorhandler

import android.util.Log

class NetworkResponse {

    private val TAG = "NetworkResponse"

  companion  object {
    val  instance = NetworkResponse()
    }


    fun networHandler(code: Int): String? {
        var msg = ""
        when (code) {
            200 -> {
                msg = "apply: ok"
                Log.d(TAG, "apply: ok")
            }
            403 -> {
                msg = "apply: wrong_password"
                Log.d(TAG, "apply:wrong_password ")
            }
            404 -> {
                msg = "apply: user_not_exist"
                Log.d(TAG, "apply: user_not_exist ")
            }
            409 -> {
                msg = "apply: user_already_exist"
                Log.d(TAG, "apply: user_already_exist")
            }
            500 -> {
                msg = "apply: internal server error"
                Log.d(TAG, "apply: internal server error")
            }
            401 -> {
                msg = "apply: not unauthorised"
                Log.d(TAG, "apply: not unauthorised")
            }
            503 -> {
                msg = "apply: Check your internet connection"
                Log.d(TAG, "apply: Check your internet connection")
            }
            else -> {
            }
        }
        return msg
    }
}