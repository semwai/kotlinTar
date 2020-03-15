package ru.spbstu.semwai

import java.io.File

//Задача класса - взять список файлов и запаковать их в 1
class TarEngine(private val files: List<String>, private val outputFile: String) {

    private lateinit var metadata: List<Schema>

    fun run() {
        metadata = files.map {
            with(File(it)) {
                Schema(this.name, this.length())
            }
        }
        with(File(outputFile)) {
            createNewFile()
            writeBytes(
                metadata.joinToString(",").toByteArray() + 0
            )
            files.forEach {
                appendBytes(File(it).readBytes())
            }
        }
    }

}