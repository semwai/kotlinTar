package ru.spbstu.semwai

import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.ExampleMode.ALL
import org.kohsuke.args4j.Option
import org.kohsuke.args4j.spi.StringArrayOptionHandler


class Tar(args: Array<String>) {


    @Option(name = "-f", handler = StringArrayOptionHandler::class, required = true)
    var inputFiles = arrayListOf("")

    @Option(name = "-u", usage = "Unpack", required = false)
    var unpackFlag = false

    @Option(name = "-out", usage = "Output file name", required = true)
    var outputFile = ""


    init {
        try {
            with(CmdLineParser(this)) {
                parseArgument(args.toMutableList())
            }

        } catch (ex: CmdLineException) {
            println(ex.message)
        }
        inputFiles.removeAt(0)


        println(unpackFlag)
        println(outputFile)
        println(inputFiles)

        require(inputFiles.size > 0) { print("no input") }


        if (unpackFlag) {
            //распаковываем
        } else {
            TarEngine(inputFiles, outputFile).run()
        }
    }
}


fun main(args: Array<String>) {
    Tar(args)
}