package com.it.yousefl.mstarttask.utils

import android.R
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar


class Utils {
    companion object {
        fun onSNACK(view: View, msg: String){
            //Snackbar(view)
            val snackbar = Snackbar.make(
                view, msg,
                Snackbar.LENGTH_LONG
            ).setAction("Action", null)
            snackbar.setActionTextColor(Color.BLUE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.LTGRAY)
            val textView =
                snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.BLUE)
            textView.textSize = 28f
            snackbar.show()
        }

        fun getDialog(context: Context, msg: String?, title: String?) {
            val alertDialog2 = AlertDialog.Builder(
                context//, R.style.CustomDialogTheme
            )
            alertDialog2.setTitle(title)
            alertDialog2.setMessage(msg)


// Setting Positive "Yes" Btn
            alertDialog2.setPositiveButton(
                context.resources.getString(R.string.ok)
            ) { dialog, which ->
                // Write your code here to execute after dialog
                //                        Toast.makeText(context,
                //                                "You clicked on YES", Toast.LENGTH_SHORT)
                //                                .show();
            }

// Setting Negative "NO" Btn
//        alertDialog2.setNegativeButton("NO",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Write your code here to execute after dialog
//                        Toast.makeText(context,
//                                "You clicked on NO", Toast.LENGTH_SHORT)
//                                .show();
//                        dialog.cancel();
//                    }
//                });

// Showing Alert Dialog
            alertDialog2.show()
        }
    }
}