package com.linkdev.demokotlin.common.problemSoloving.warm_up

// Complete the plusMinus function below.
fun plusMinus(arr: Array<Int>): Unit {
    var plusValues = 0.0
    var minusValues = 0.0
    var zeroValues = 0.0

    arr.forEachIndexed { _, v ->
        when {
            v == 0 -> zeroValues++
            v > 0 -> plusValues++
            v < 0 -> minusValues++
        }

    }

    println("%.6f".format(plusValues / arr.size))
    println("%.6f".format(minusValues / arr.size))
    println("%.6f".format(zeroValues / arr.size))
}

fun main(args: Array<String>) {

    plusMinus(arrayOf(-1, 1, 0, 5, 9))
}
