package com.hbravodev.rest2serve.model

object Tables {
    private val tables: List<Table> = listOf(
//            Table(1, null),
//            Table(2),
//            Table(3),
//            Table(4),
//            Table(5),
//            Table(6),
//            Table(7),
            Table(8, null)
    )

    val count
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun toArray() = tables.toTypedArray()
}