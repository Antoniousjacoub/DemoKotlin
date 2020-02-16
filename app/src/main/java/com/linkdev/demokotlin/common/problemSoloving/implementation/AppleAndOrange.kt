package com.linkdev.demokotlin.common.problemSoloving.implementation


// Complete the countApplesAndOranges function below.
fun countApplesAndOranges(
    s: Int,
    t: Int,
    a: Int,
    b: Int,
    apples: Array<Int>,
    oranges: Array<Int>
): Unit {

    var applesResult = 0
    var orangesResult = 0
    if (apples.size == oranges.size) {

        apples.forEachIndexed { index, value ->
            val sumApples = value + a
            val sumOrange = oranges[index] + b
            if (sumApples in s..t)
                applesResult++

            if (sumOrange in s..t)
                orangesResult++
        }
    } else {
        apples.forEach {
            val sum = it + a
            if (sum in s..t)
                applesResult++

        }
        oranges.forEach {
            val sum = it + b
            if (sum in s..t)
                orangesResult++

        }
    }
    println(applesResult)
    println(orangesResult)
}

fun main(args: Array<String>) {

    countApplesAndOranges(7, 10, 4, 12, arrayOf(2, 3, -4), arrayOf(3, -2, -4))
}