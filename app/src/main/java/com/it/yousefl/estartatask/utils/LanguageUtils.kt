package com.it.yousefl.mstarttask.utils

import android.content.Context
import android.text.TextUtils
import java.util.*

class LanguageUtils {

    companion object {
        fun updateLanguage(ctx: Context, lang: String?) {
            val resources = ctx.resources
            val cfg = resources.configuration
            if (!TextUtils.isEmpty(lang)) {
                cfg.locale = Locale(lang)
            } else {
                cfg.locale = Locale.getDefault()
            }
            Locale.setDefault(cfg.locale)
            resources.updateConfiguration(cfg, resources.displayMetrics)
        }
    }


}