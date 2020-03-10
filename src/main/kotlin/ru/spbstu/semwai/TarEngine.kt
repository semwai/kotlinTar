package ru.spbstu.semwai

import java.io.File

//Задача класса - взять список файлов и запаковать их в 1
class TarEngine(private val files: List<String>, private val outputFile: String) {

    private val metadata = mutableListOf<Schema>()

    fun run() {
        files.forEach {
            with(File(it)) {
                metadata += Schema(this.name, this.length())
            }
        }
        with(File(outputFile)) {
            createNewFile()
            writeText(
                metadata.fold("") { v: String, it: Schema ->
                    v + it.toString()
                }.dropLast(1) + '!'
            )
            files.forEach {
                appendText(File(it).readText())
            }
        }
    }

}