package com.linkdev.demokotlin.common.problemSoloving

import kotlin.math.abs

// Complete the minimumBribes function below.
fun minimumBribes(q: Array<Int>) {
    var numberOfBribes = 0
    var numberOfSkips = 0
    var j = 0
    while (q.size > j + 1) {
        if (q[j] > (j + 3)) {
            println("Too chaotic")
            return
        }
        if (q[j] - q[j + 1] > 0) {

            if (abs(q[j] - q[j + 1]) == 1)
                numberOfBribes++

            numberOfBribes += q[j] - q[j + 1]
        }

        j++
    }


    println(numberOfBribes)
}

fun main(args: Array<String>) {
    val array: Array<Int> = arrayOf(
        1, 2, 5, 3, 7, 8, 6, 4

    )
    minimumBribes(array)

}