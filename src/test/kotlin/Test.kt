import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.spbstu.semwai.Tar
import java.io.File

class Test {

    val path = "src/test/kotlin";
    val f1 = "$path/1.txt"
    val f2 = "$path/2.txt"
    val f3 = "$path/3.jpg"
    val fout = "$path/out.tar"

    @Before
    fun init(){
        File(f1).run {
            writeText("abcdef")
        }
        File(f2).run {
            writeText("123456")
        }

    }

    @Test
    fun test1() {


        Tar("-f $f1 $f2 $f3 -out $fout".split(' ').toTypedArray())
    }


    @After
    fun clear(){

    }

}