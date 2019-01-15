package com.ivanloy.nutricards.util

import android.text.Html
import android.os.Build
import android.text.Spanned


class TextUtil {
    /**
     * Created by ivanloy on 15/01/19.
     */
    companion object {
        fun fromHtml(html: String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(html)
            }
        }
    }

}