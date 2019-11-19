package ru.bmstu.krygin

import java.io.File
import java.io.PrintWriter
import kotlin.math.absoluteValue

class BrownRobinsonMatrixGame(private val matrix: Matrix, private val accuracy: Double) {

    fun play() {
        // Накопленный выигрыш
        val winX = Array(matrix.n) { 0.0 }
        val winY = Array(matrix.m) { 0.0 }

        // step 0
        var strategyX = 0
        var strategyY = 0

        var k = 0


        val fileWriter = File("matrix_game.csv").printWriter()
        writeHeader(fileWriter)
        logHeader(winX, winY)

        do {
            k++
            for (i in winX.indices) {
                winX[i] += matrix.element(i, strategyY)
            }
            for (j in winY.indices) {
                winY[j] += matrix.element(strategyX, j)
            }

            val max = winX.max()!!
            val min = winY.min()!!

            val xAverageWin = max / k.toDouble()
            val yAverageWin = min / k.toDouble()

            writeStep(fileWriter, k, xAverageWin, yAverageWin)
            logStep(k, strategyX, strategyY, winX, winY, xAverageWin, yAverageWin)

            strategyX = winX.indexOf(max)
            strategyY = winY.indexOf(min)


        } while ((xAverageWin - yAverageWin).absoluteValue > accuracy)

        fileWriter.close()
    }

    private fun writeHeader(fileWriter: PrintWriter) {
        fileWriter.apply {
            val columns = listOf("k", "minValue", "maxValue")
            println(columns.joinToString())
        }
    }

    private fun writeStep(fileWriter: PrintWriter, k: Int, xAverageWin: Double, yAverageWin: Double) {
        fileWriter.apply {
            val values = listOf(
                k.toString(),
                xAverageWin.toString(),
                yAverageWin.toString()
            )
            println(values.joinToString())
        }
    }

    private fun logHeader(winX: Array<Double>, winY: Array<Double>) {
        print("k".padStart(4))
        print("x".padStart(2))
        print("y".padStart(2))
        for (i in winX.indices) print("x$i".padStart(4))
        for (i in winY.indices) print("y$i".padStart(4))
        println()
    }

    private fun logStep(k: Int, strategyX: Int, strategyY: Int, winX: Array<Double>, winY: Array<Double>, xAverageWin: Double, yAverageWin: Double) {
        print(k.toString().padStart(6))
        print(strategyX.toString().padStart(2))
        print(strategyY.toString().padStart(2))
        winX.forEach { print(it.toInt().toString().padStart(10)) }
        winY.forEach { print(it.toInt().toString().padStart(10)) }
        print(String.format("%.2f", xAverageWin).padStart(10))
        print(String.format("%.2f", yAverageWin).padStart(10))
        println()
    }
}