package ru.spbstu.semwai

import java.io.File

class UnTarEngine(val inputFIle: String) {

    val metadata = mutableListOf<Schema>()

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


        val parse = String(rawHeader.toByteArray()).replace(",,", ",").dropLast(1)

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