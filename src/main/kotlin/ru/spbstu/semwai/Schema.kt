package ru.spbstu.semwai

class Schema(val name: String, val length: Long){

    override fun toString(): String {
        return "$name $length"
    }
}

fun parseSchema(string: String): List<Schema> {

    return string.split(',').map {
        with(it.split(' ')){
            Schema(this[0], this[1].toLong())
        }
    }
}