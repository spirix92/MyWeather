package com.selen.myweather.extension

import android.text.Editable

/**
 * @author Pyaterko Aleksey
 */
fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)