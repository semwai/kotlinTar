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
        print(metadata)
        with(File(outputFile)){
            createNewFile()
            appendText("${metadata.size},")
            appendText(metadata.fold("") { v: String, it: Schema -> v + it.toString()})



            files.forEach {
                appendBytes(File(it).readBytes())
            }
        }
    }

}


