package ru.merisen.javafileparser

import javax.swing.*
import javax.swing.event.CaretEvent
import javax.swing.event.CaretListener
import java.io.*
import java.util.logging.Level
import java.util.logging.Logger

object MainKotlin {

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val wframe = frame(readDataFile("your path to file")!!)
            wframe.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            wframe.isVisible = true
        } catch (ex: IOException) {
            Logger.getLogger(Main::class.java!!.getName()).log(Level.SEVERE, null, ex)
        }

    }

    @Throws(FileNotFoundException::class, IOException::class)
    internal fun readDataFile(name: String): Array<String>? {
        try {
            val sym = InputStreamReader(FileInputStream(name), "CP1251")
            val c = CharArray(File(name).length().toInt())
            val a: Array<String>? = null
            val i = 0
            val str: String
            sym.read(c)
            str = String(c)
            sym.close()
            return str.split("\r\n".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        } catch (e: Exception) {
            println("Неверное название")
        }

        return null
    }

}


internal class frameNew(s: Array<String>) : JFrame() {
    private var columns = 90

    init {
        this.setSize(690, 890)
        this.isResizable = true
        val panel = JPanel()
        val text = JTextField()
        text.isEditable = false
        text.columns = columns
        text.addCaretListener {
            val len = text.document.length
            if (len > columns)
                text.columns = ++columns
            else {
                if (--columns != 0)
                    text.columns = columns
                else {
                    columns = 50
                    text.columns = columns
                }
            }
            panel.revalidate()
            panel.repaint()
        }
        val textArea = JTextArea()
        textArea.isEditable = false
        textArea.setSize(1500, 1500)

        panel.add(textArea)
        add(panel)


        var s1 = ""
        for (i in s.indices)
            s1 = s1 + s[i]
        textArea.text = s1
    }

}