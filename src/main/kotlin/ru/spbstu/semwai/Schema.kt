package ru.spbstu.semwai

class Schema(val name: String, val length: Long){

    override fun toString(): String {
        return "$name $length,"
    }
}

fun parseSchema(string: String): List<Schema> {

    val out = mutableListOf<Schema>()

    string.split(',').forEach {
        val row = it.split(' ')
        out += Schema(row[0], row[1].toLong())
    }

    return out
}