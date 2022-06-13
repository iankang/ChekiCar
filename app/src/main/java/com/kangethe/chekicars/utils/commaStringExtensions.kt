package com.kangethe.chekicars.utils

import java.text.NumberFormat

object commaStringExtensions {
    val Int.commaString: String
        get() = NumberFormat.getInstance().format(this)

    val String.commaString: String
        get() = NumberFormat.getNumberInstance().format(this.toInt())

    val Long.commaString: String
        get() = NumberFormat.getInstance().format(this)

    val Double.commaString: String
        get() = NumberFormat.getInstance().format(this)

    val Number.commaString: String
        get() = NumberFormat.getInstance().format(this)

    val Float.commaString: String
        get() = NumberFormat.getInstance().format(this)
}