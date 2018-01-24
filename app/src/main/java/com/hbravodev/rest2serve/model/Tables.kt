package com.hbravodev.rest2serve.model

object Tables {
    private val tables: List<Table> = listOf(
            Table(1, null),
            Table(2, null),
            Table(3, null),
            Table(4, null),
            Table(5, null),
            Table(6, null),
            Table(7, null),
            Table(8, null)
    )

    val count
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun toArray() = tables.toTypedArray()
}