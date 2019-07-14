package com.ariorick.revoluttestapp.model

data class Currency(
    val name: String,
    val base: String,
    val value: Float
) : Comparable<Currency> {

    val isBase = name == base

    override fun compareTo(other: Currency): Int {
        return if (isBase.compareTo(other.isBase) == 0) {
            name.compareTo(other.name)
        } else -1 * isBase.compareTo(other.isBase)
    }
}