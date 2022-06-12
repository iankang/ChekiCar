package com.kangethe.chekicars.utils

import java.text.NumberFormat

object commaStringExtensions {
    val Int.commaString: String
        get() = NumberFormat.getInstance().format(this)

    val String.commaString: String
        get() = NumberFormat.getNumberInstance().format(this.toDouble())

    val Long.commaString: String
        get() = NumberFormat.getInstance().format(this)

    val Double.commaString: String
        get() = NumberFormat.getInstance().format(this)
}