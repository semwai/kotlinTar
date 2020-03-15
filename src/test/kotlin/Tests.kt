import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import ru.spbstu.semwai.Tar
import java.io.File
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException

@Suppress("NonAsciiCharacters")
class Tests {
    private val f1 = "1.txt"
    private val f2 = "2.txt"
    private val f3 = "3.txt"
    private val fout = "out.txt"

    private fun runTar(s : String) = Tar(s.split(' ').toTypedArray())

    @Test
    fun `🐷 проверка работы базового функционала`() {
        with(File(f1)) {
            writeText("abcdefРусские буквы для проверки")
        }
        with(File(f2)) {
            val str = StringBuilder()
            for (i in 1..1000000)
                str.append(i)
            writeText(str.toString())
        }

        with(File(f3)) {
            writeText("123456")
        }
        runTar("$f1 $f2 $f3 -out $fout")
        File(f1).delete()
        File(f2).delete()
        File(f3).delete()
        runTar("$fout -u")
        assertEquals("abcdefРусские буквы для проверки", File(f1).readText())
        assertEquals("123456", File(f3).readText())
        File(f1).delete()
        File(f2).delete()
        File(f3).delete()
        File(fout).delete()
    }
    @Test
    fun `🐓проверка работы механизма исключений`(){
        File(fout).delete()
        assertThrows(FileNotFoundException::class.java) {
            runTar("$fout -u")
        }
        File(fout).writeText("hello world")
        assertThrows(IllegalArgumentException::class.java) {
            runTar("$fout -u")
        }
        File(fout).writeText("1.txt 1,2.txt 2a!12")
        assertThrows(Exception::class.java) {
            runTar("$fout -u")
        }
        File(fout).delete()
    }
}