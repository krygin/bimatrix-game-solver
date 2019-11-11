import kotlin.math.absoluteValue
import kotlin.random.Random

fun main(arguments: Array<String>) {
    val n = 10
    val m = 10
    val defenderPaymentMatrix = Array(n) {IntArray(m) {Random.nextInt(0, 100)} }
    val attackerPaymentMatrix = Array(n) {IntArray(m) {Random.nextInt(0, 100)} }


    playMatrixGame(n, m)

    print("Hello world!!!")
}

fun playMatrixGame(n: Int, m: Int) {
    val paymentMatrix = Array(n) {Array(m) {Random.nextDouble(0.0, 100.0)} }

    val pX = Array(n) {0}
    val pY = Array(m) {0}

    val pV1 = Array(n) { 0.0 }
    val pV2 = Array(m) { 0.0 }

    val maxMin = getMaxMin(paymentMatrix)

    pX[maxMin.i]++

    for(i in 0 until m) pV2[i] += paymentMatrix[maxMin.i][i]

    var k1 = 1
    var k2 = 0

    var vMin = 0.0
    var vMax = 0.0

    do {
        // Выбор второго игрока
        var min = Double.MAX_VALUE
        var iMin = 0
        for (i in 0 until m)
            if (pV2[i] < min) {
                min = pV2[i]
                iMin = i
            }
        pY[iMin]++
        k2++
        vMin = min / k1 // Верхняя цена игры
        for (i in 0 until n) pV1[i] += paymentMatrix[i][iMin]

        var max = 0.0
        var iMax = 0
        for (i in 0 until n)
            if (pV1[i] > max) {
                max = pV1[i]
                iMax = i
            }
        pX[iMax]++
        k1++
        vMax = max / k2 // Нижняя цена игры
        for (i in 0 until m) pV2[i] += paymentMatrix[iMax][i]
        println("maxValue = $vMax minValue = $vMin delta = ${(vMax - vMin).absoluteValue}")

    } while ((vMax - vMin).absoluteValue > 0.001)

    val sumX = pX.sum().toDouble()
    pX.forEach { print("${it/sumX} ") }
    println()

    val sumY = pY.sum().toDouble()
    pY.forEach { print("${it/sumY} ") }
    println()

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

data class MaxMin(val value: Double, val i: Int) {

}