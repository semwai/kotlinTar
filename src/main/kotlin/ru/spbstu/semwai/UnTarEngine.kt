package ru.spbstu.semwai

import java.io.File
import java.lang.IllegalArgumentException

//Класс занимается распаковкой файлов
class UnTarEngine(private val inputFIle: String) {

    private val metadata = mutableListOf<Schema>()

    fun run() {
        val data = File(inputFIle).readBytes()

        val rawHeader = mutableListOf<Byte>()
        for (byte in data){
            if (byte.toInt() != 0){
                rawHeader.add(byte)
            }
            else
                break
        }
        if (rawHeader.size == data.size)
            throw IllegalArgumentException("Invalid file")
        val parse = String(rawHeader.toByteArray())
        var offset = rawHeader.size + 1
        metadata += parseSchema(parse)
        metadata.forEach {
            println("Extract file ${it.name} with size ${it.length} byte")
            with(File(it.name)){
                createNewFile()
                writeBytes(data.drop(offset).take(it.length.toInt()).toByteArray())
            }
            offset += it.length.toInt()
        }
    }
}