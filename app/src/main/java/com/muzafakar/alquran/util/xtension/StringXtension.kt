package com.muzafakar.alquran.util.xtension

fun String.wordCaps() = this.split(" ").joinToString(" ") { it.capitalize() }