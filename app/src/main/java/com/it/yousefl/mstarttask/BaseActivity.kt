package com.it.yousefl.mstarttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dmax.dialog.SpotsDialog

abstract class BaseActivity : AppCompatActivity() {

    var dialog: SpotsDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = SpotsDialog(this, R.style.Custom)
        dialog!!.setTitle(getString(R.string.loading))
        dialog!!.setCancelable(false)
    }

    open fun showDialog() {
        dialog!!.show()
    }

    open fun hideDialog() {
        dialog!!.dismiss()
    }
}