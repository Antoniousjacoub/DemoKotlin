package com.linkdev.demokotlin.common.problemSoloving.warm_up

// Complete the miniMaxSum function below.
fun miniMaxSum(arr: Array<Int>): Unit {

    var maxSumValue = 0L
    var miniSumValue = 0L
    val sortedArr = arr.sortedArray()
    val miniArr = sortedArr.take(4)
    val maxArr = sortedArr.takeLast(4)
    miniArr.forEachIndexed { _: Int, value: Int ->
        miniSumValue += value
    }
    maxArr.forEachIndexed { _: Int, value: Int ->
        maxSumValue += value
    }

    println(("$miniSumValue $maxSumValue"))

}

fun main(args: Array<String>) {

    miniMaxSum(arrayOf(1, 6, 5, 8, 9, 7, 8, 5))
}