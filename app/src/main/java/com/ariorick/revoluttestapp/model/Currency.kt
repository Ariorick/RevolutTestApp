package com.ariorick.revoluttestapp.model

data class Currency(
    val name: String,
    val base: String,
    val value: Float
) : Comparable<Currency> {

    override fun compareTo(other: Currency): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}