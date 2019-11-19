package ru.bmstu.krygin

import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.random.Random

fun main(arguments: Array<String>) {
    val n = 10
    val m = 5

//    defenderPaymentMatrix.print()
//
//    println("Row Example")
//    defenderPaymentMatrix.row(2).print()
//
//    println("Column Example")
//    defenderPaymentMatrix.column(2).print()

    val gameMatrix = Matrix(Array(m) { Array(n) {Random.nextDouble(0.0, 100.0)}})
    playMatrixGame(gameMatrix, 100.0/100)

    val defenderPaymentMatrix = Matrix(Array(m) { Array(n) { Random.nextDouble(0.0, 100.0) } })
    val attackerPaymentMatrix = Matrix(Array(m) { Array(n) { Random.nextDouble(0.0, 100.0) } })
    playBiMatrixGame(defenderPaymentMatrix, attackerPaymentMatrix, 100.0/100)
//
    print("Hello world!!!")
}

fun playBiMatrixGame(defenderPaymentMatrix: Matrix, attackerPaymentMatrix: Matrix, accuracy: Double) {

    // Накопленный выигрыш
    val winX = Array(defenderPaymentMatrix.n) { 0.0 }
    val winY = Array(attackerPaymentMatrix.m) { 0.0 }

    // step 0
    var strategyX = 0
    var strategyY = 0

    var k = 0


    val fileWriter = File("bi_matrix_game.csv").printWriter()
    fileWriter.apply {
        val columns = listOf(
            "k",
            "minValue",
            "maxValue"
        )
        println(columns.joinToString())
    }
    print("k".padStart(4))
    print("x".padStart(2))
    print("y".padStart(2))
    for (i in winX.indices) print("x$i".padStart(4))
    for (i in winY.indices) print("y$i".padStart(4))
    println()

    do {
        k++
        for (i in winX.indices) {
            winX[i] += defenderPaymentMatrix.element(i, strategyY)
        }
        for (j in winY.indices) {
            winY[j] += attackerPaymentMatrix.element(strategyX, j)
        }

        val max = winX.max()!!
        val min = winY.min()!!

        val xAverageWin = max / k.toDouble()
        val yAverageWin = min / k.toDouble()

        print(k.toString().padStart(6))
        print(strategyX.toString().padStart(2))
        print(strategyY.toString().padStart(2))
        winX.forEach { print(it.toInt().toString().padStart(10)) }
        winY.forEach { print(it.toInt().toString().padStart(10)) }
        print(String.format("%.2f", xAverageWin).padStart(10))
        print(String.format("%.2f", yAverageWin).padStart(10))
        println()

        fileWriter.apply {
            val values = listOf(
                k.toString(),
                xAverageWin.toString(),
                yAverageWin.toString()
            )
            println(values.joinToString())
        }

        strategyX = winX.indexOf(max)
        strategyY = winY.indexOf(min)


    } while (k < 10000)

    fileWriter.close()}


fun playMatrixGame(matrix: Matrix, accuracy: Double) {

    // Накопленный выигрыш
    val winX = Array(matrix.n) { 0.0 }
    val winY = Array(matrix.m) { 0.0 }

    // step 0
    var strategyX = 0
    var strategyY = 0

    var k = 0


    val fileWriter = File("matrix_game.csv").printWriter()
    fileWriter.apply {
        val columns = listOf(
            "k",
            "minValue",
            "maxValue"
        )
        println(columns.joinToString())
    }
    print("k".padStart(4))
    print("x".padStart(2))
    print("y".padStart(2))
    for (i in winX.indices) print("x$i".padStart(4))
    for (i in winY.indices) print("y$i".padStart(4))
    println()

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

        print(k.toString().padStart(6))
        print(strategyX.toString().padStart(2))
        print(strategyY.toString().padStart(2))
        winX.forEach { print(it.toInt().toString().padStart(10)) }
        winY.forEach { print(it.toInt().toString().padStart(10)) }
        print(String.format("%.2f", xAverageWin).padStart(10))
        print(String.format("%.2f", yAverageWin).padStart(10))
        println()

        fileWriter.apply {
            val values = listOf(
                k.toString(),
                xAverageWin.toString(),
                yAverageWin.toString()
            )
            println(values.joinToString())
        }

        strategyX = winX.indexOf(max)
        strategyY = winY.indexOf(min)


    } while ((xAverageWin - yAverageWin).absoluteValue > accuracy)

    fileWriter.close()
}