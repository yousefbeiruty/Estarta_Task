package com.it.yousefl.mstarttask.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.it.yousefl.mstarttask.BaseApplication


class PreferenceUtil {

//    companion object {
//        private fun getDefaultSharedPreference(context: Context): SharedPreferences? {
//            return if (PreferenceManager.getDefaultSharedPreferences(
//                    BaseApplication.getInstance().getApplicationContext()
//                ) != null
//            ) PreferenceManager.getDefaultSharedPreferences(
//                BaseApplication.getInstance().getApplicationContext()
//            ) else null
//        }
//
//        fun setSelectedLanguageId(id: String?) {
//            val prefs =
//                getDefaultSharedPreference(BaseApplication.getInstance().getApplicationContext())
//            val editor = prefs!!.edit()
//            editor.putString("app_language_id", id)
//            editor.apply()
//        }
//
//        fun getSelectedLanguageId(): String? {
//            return getDefaultSharedPreference(BaseApplication.getInstance().getApplicationContext()).getString("app_language_id", "en")
//        }
//    }
}