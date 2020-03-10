package ru.spbstu.semwai

import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import org.kohsuke.args4j.spi.StringArrayOptionHandler
import kotlin.system.exitProcess


class Tar(args: Array<String>) {


    @Argument(
        usage = "Input files for packing or single file for unpacking",
        required = true,
        handler = StringArrayOptionHandler::class,
        metaVar = "file1 file2 file3 ..."
    )
    var inputFiles = arrayListOf("")

    @Option(name = "-u", usage = "Unpack", required = false)
    var unpackFlag = false

    @Option(name = "-out", metaVar = "filename",usage = "Output file name", required = false)
    var outputFile = "out.tar"


    init {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(args.toMutableList())

        } catch (ex: CmdLineException) {
            System.err.println(ex.message)
            parser.printUsage(System.out)
        }
        inputFiles.removeAt(0)

        if (inputFiles.size == 0) {
            print("Empty input")
            exitProcess(-1)
        }
        if (unpackFlag) {
            UnTarEngine(inputFiles[0]).run()
        } else {
            println("Make tar file")
            TarEngine(inputFiles, outputFile).run()
        }
    }
}