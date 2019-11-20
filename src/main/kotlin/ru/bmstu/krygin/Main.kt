package ru.bmstu.krygin

import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.random.Random

fun main(arguments: Array<String>) {
    val n = 10
    val m = 5

    val gameMatrix = Matrix(Array(m) { Array(n) {Random.nextDouble(0.0, 100.0)}})
//    BrownRobinsonMatrixGame(matrix = gameMatrix, accuracy = 1.0).play()

    val defenderPaymentMatrix = Matrix(arrayOf(
        arrayOf(54, 66, 71, 60, 97, 33, 56, 32, 92),
        arrayOf(92, 95, 52, 74, 91, 18, 46, 33, 4),
        arrayOf(5, 16, 57, 63, 21, 52, 79, 26, 20),
        arrayOf(88, 93, 18, 29, 86, 53, 37, 62, 97),
        arrayOf(90, 35, 99, 63, 23, 54, 37, 30, 14)
    ).map { it.map { item -> item.toDouble() }.toTypedArray() }.toTypedArray())

    val attackerPaymentMatrix = Matrix( arrayOf(
        arrayOf(75, 62, 92, 22, 60, 21, 60, 20, 59),
        arrayOf(38, 31, 94, 1, 11, 34, 45, 48, 81),
        arrayOf(89, 81, 9, 67, 58, 44, 9, 50, 18),
        arrayOf(33, 89, 77, 95, 40, 89, 84, 38, 76),
        arrayOf(96, 90, 8, 89, 96, 37, 59, 62, 45)
    ).map { it.map { item -> item.toDouble() }.toTypedArray() }.toTypedArray())
//    val defenderPaymentMatrix = Matrix(Array(m) { Array(n) { Random.nextInt(0, 100).toDouble() } })
//    val attackerPaymentMatrix = Matrix(Array(m) { Array(n) { Random.nextInt(0, 100).toDouble() } })
    BrownRobinsonBiMatrixGame(defenderMatrix = defenderPaymentMatrix, attackerMatrix = attackerPaymentMatrix, accuracy = 1.0).play()

    print("Hello world!!!")
}