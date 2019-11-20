package ru.bmstu.krygin

import java.io.File
import java.io.PrintWriter
import kotlin.math.absoluteValue

class BrownRobinsonBiMatrixGame(private val defenderMatrix: Matrix, private val attackerMatrix: Matrix, private val accuracy: Double) {

    fun play() {
        println("Defender matrix")
        defenderMatrix.print()
        println("Attacker matrix")
        attackerMatrix.print()


        // Накопленный выигрыш
        val winX = Array(defenderMatrix.n) { 0.0 }
        val winY = Array(attackerMatrix.m) { 0.0 }

        var k = 0


        val fileWriter = File("bi_matrix_game.csv").printWriter()
        writeHeader(fileWriter)
//        logHeader(winX, winY)

        var currentXWin = 0.0
        var currentYWin = 0.0

        do {

            val strategyX = winX.indexOf(winX.max()!!)
            val strategyY = winY.indexOf(winY.max()!!)

            k++
            for (i in winX.indices) {
                winX[i] += defenderMatrix.element(i, strategyY)
            }
            for (j in winY.indices) {
                winY[j] += attackerMatrix.element(strategyX, j)
            }

            val xAverageWin = winX.max()!! / k.toDouble()
            val yAverageWin = winY.max()!! / k.toDouble()

            currentXWin = xAverageWin
            currentYWin = yAverageWin

            writeStep(fileWriter, k, xAverageWin, yAverageWin)
//            logStep(k, strategyX, strategyY, winX, winY, xAverageWin, yAverageWin)

        } while (k < 8000)

        fileWriter.close()

        logResult(k, currentXWin, currentYWin)
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

    private fun logResult(k: Int, xAverageWin: Double, yAverageWin: Double) {
        println("Game Result: $k $xAverageWin $yAverageWin")
    }
}