package com.nurhidayaatt.moviecatalog.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

fun String.formatString(): String {
    val value = this.replace("[^\\d]".toRegex(), "")
    val doubleValue = value.toDouble()
    return DecimalFormat.getInstance().format(doubleValue)
}

fun View.showSnackBar(message: String) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackBar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
    snackBar.show()
}