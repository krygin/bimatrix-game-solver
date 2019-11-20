package ru.bmstu.krygin

class Matrix(private val matrix: Array<Array<Double>>) {
    val m: Int
    val n: Int

    init {
        val elementSizes = matrix.map { it.size }.distinct()
        if (elementSizes.size > 1) {
            throw IllegalStateException()
        }
        m = elementSizes[0]
        n = matrix.size
    }

    fun element(i: Int, j: Int): Double {
        return matrix[i][j]
    }

    fun print() {
        for (i in matrix.indices) {
            println(matrix[i].map { it.toInt() }.joinToString(separator = " "))
        }
    }
}