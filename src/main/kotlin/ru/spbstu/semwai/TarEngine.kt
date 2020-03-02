package ru.spbstu.semwai

import java.io.File

//Задача класса - взять список файлов и запаковать их в 1
class TarEngine(val files: List<String>, val outputFile: String) {

    val metadata = mutableListOf<Schema>()

    fun run(){
        files.forEach {
           with(File(it)){
               metadata += Schema(this.name, this.length())
           }
        }
        println(metadata)
        with(File(outputFile)){
            createNewFile()
            writeBytes(metadata.fold("") { v: String, it: Schema -> v + it.toString()}.toByteArray())
            appendBytes(byteArrayOf(0))



            files.forEach {
                appendBytes(File(it).readBytes())
            }
        }
    }

}


