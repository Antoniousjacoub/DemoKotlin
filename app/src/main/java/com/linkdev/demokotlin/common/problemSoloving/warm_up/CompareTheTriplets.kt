package com.linkdev.demokotlin.common.problemSoloving.warm_up

fun compareTriplets(a: Array<Int>, b: Array<Int>): Array<Int> {
    var aliceScore = 0
    var bobScore = 0

    a.forEachIndexed { index, value ->

        if (value > b[index]) {
            aliceScore++
        } else if (value < b[index]) {
            bobScore++
        }

    }

    return arrayOf(aliceScore,bobScore)

}

fun main(args: Array<String>) {

    val result = compareTriplets(
        arrayOf(1, 5, 8),
        arrayOf(1, 4, 5)
    )

}