package com.linkdev.demokotlin.common.problemSoloving.warm_up

// Complete the birthdayCakeCandles function below.
fun birthdayCakeCandles(ar: Array<Int>): Int {

    val sortedArr = ar.sortedArray()
    val lastOne = sortedArr[sortedArr.size-1]
    val candlesBlowOut = sortedArr.takeLastWhile {
         lastOne == it
    }

    return candlesBlowOut.size

}

fun main(args: Array<String>) {
    birthdayCakeCandles(arrayOf(3, 2, 1, 3))

}
