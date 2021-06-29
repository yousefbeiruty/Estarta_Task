package com.it.yousefl.mstarttask.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class Utils {
    companion object {
        fun onSNACK(view: View,msg:String){
            //Snackbar(view)
            val snackbar = Snackbar.make(view, msg,
                Snackbar.LENGTH_LONG).setAction("Action", null)
            snackbar.setActionTextColor(Color.BLUE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.LTGRAY)
            val textView =
                snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.BLUE)
            textView.textSize = 28f
            snackbar.show()
        }
    }
}