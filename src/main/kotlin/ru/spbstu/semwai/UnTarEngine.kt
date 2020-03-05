package ru.spbstu.semwai

import java.io.File
import java.lang.IllegalArgumentException

//Класс занимается распакованием файлов
class UnTarEngine(private val inputFIle: String) {

    private val metadata = mutableListOf<Schema>()

    fun run() {
        val data = File(inputFIle).readText()
        val parse = data
            .split("!")
            .firstOrNull() ?: throw IllegalArgumentException("Invalid file")
        var offset = parse.length + 1
        metadata += parseSchema(parse)
        metadata.forEach {
            println("Extract file ${it.name} with size ${it.length} byte")
            with(File(it.name)) {
                writeText(data.drop(offset).take(it.length.toInt()))
            }
            offset += it.length.toInt()
        }

    }
}