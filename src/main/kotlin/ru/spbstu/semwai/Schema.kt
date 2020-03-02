package ru.spbstu.semwai

class Schema(val name: String, val length: Long){

    override fun toString(): String {
        return "$name $length,"
    }
}