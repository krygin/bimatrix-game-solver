import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.random.Random

fun main(arguments: Array<String>) {
    val n = 10
    val m = 5
    val defenderPaymentMatrix = Array(m) {Array(n) {Random.nextDouble(0.0, 100.0)} }
    val attackerPaymentMatrix = Array(m) {Array(n) {Random.nextDouble(0.0, 100.0)} }

//    defenderPaymentMatrix.print()
//
//    println("Row Example")
//    defenderPaymentMatrix.row(2).print()
//
//    println("Column Example")
//    defenderPaymentMatrix.column(2).print()

    playMatrixGame(n, m)
//
    print("Hello world!!!")
}

fun playMatrixGame(n: Int, m: Int) {
//    val paymentMatrix = Array(n) {Array(m) {Random.nextDouble(0.0, 100.0)} }

    // n = 4
    // m = 5
    val paymentMatrix = arrayOf(
        arrayOf(4.0, 1.0, -2.0, -1.0, 0.0),
        arrayOf(2.0, 3.0, 2.0, 0.0 , 1.0),
        arrayOf(1.0, 0.0, 2.0, 3.0, 2.0),
        arrayOf(0.0, -1.0, -2.0, 1.0, 4.0)
    )

    // Накопленный выигрыш
//    val winX = Array(n) { 0.0 }
//    val winY = Array(m) { 0.0 }

    val winX = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
    val winY = arrayOf(0.0, 0.0, 0.0, 0.0)

    // step 0
    var strategyX = 0
    var strategyY = 0

    var k = 0

    print("k".padStart(4))
    print("x".padStart(2))
    print("y".padStart(2))
    for (i in 0 until winX.size) print("x$i".padStart(4))
    for (i in 0 until winY.size) print("y$i".padStart(4))
    println()

    do {
        k++;
        for (i in 0 until winX.size) {
            winX[i] += paymentMatrix[strategyY][i]
        }
        for (i in 0 until winY.size) {
            winY[i] += paymentMatrix[i][strategyX]
        }

        val max = winX.max()!!
        val min = winY.min()!!

        val xAverageWin = max/k.toDouble()
        val yAverageWin = min/k.toDouble()

        print(k.toString().padStart(6))
        print(strategyX.toString().padStart(2))
        print(strategyY.toString().padStart(2))
        winX.forEach { print(it.toInt().toString().padStart(10)) }
        winY.forEach { print(it.toInt().toString().padStart(10)) }
        print(String.format("%.2f", xAverageWin).padStart(10))
        print(String.format("%.2f", yAverageWin).padStart(10))
        println()

        strategyX = winX.indexOf(max)
        strategyY = winY.indexOf(min)


    } while (k < 8000)
}

fun getMaxMin(matrix: Array<Array<Double>>): MaxMin {
    var min: Double
    var max = 0.0

    var iMax = 0

    for (i in 0 until matrix.size) {
        min = matrix[i][0]
        for (j in 1 until matrix[i].size)
            if (min > matrix[i][j])
                min = matrix[i][j]
        if (max < min) {
            max = min
            iMax = i
        }

    }
    return MaxMin(max, iMax)
}

fun Array<Array<Double>>.row(index: Int) = this[index]

fun Array<Array<Double>>.column(index: Int) = flatMap { listOf(it[index]) }.toTypedArray()

fun Array<Double>.print() {
    for(i in 0 until size) {
        print(this[i].roundToInt().toString().padStart(4))
    }
    println()
}

fun Array<Array<Double>>.print() {
    for (i in 0 until size) {
        for (j in 0 until this[i].size) {
            print(this[i][j].roundToInt().toString().padStart(4))
        }
        println()
    }
}

data class MaxMin(val value: Double, val i: Int) {

}