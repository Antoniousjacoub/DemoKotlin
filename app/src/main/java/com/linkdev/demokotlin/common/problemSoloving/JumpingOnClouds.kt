package com.linkdev.demokotlin.common.problemSoloving


// Complete the jumpingOnClouds function below.
fun jumpingOnClouds(c: Array<Int>): Int {
    var numOfJumps = 0
    var index = 0

    do {

        if (isSafe(index + 1, c) && isSafe(index + 2, c)) {
            index += 2
            numOfJumps++
        } else if (isSafe(index + 1, c) && !isSafe(index + 2, c)) {
            index++
            numOfJumps++

        } else if (!isSafe(index + 1, c) && isSafe(index + 2, c)) {
            index+= 2
            numOfJumps++

        } else if (isSafe(index + 1, c)) {
            index++
            numOfJumps++
        } else {
            index++
        }
    } while (index - 1 != c.size && index - 1 <= c.size)


    return numOfJumps
}

fun isSafe(index: Int, array: Array<Int>): Boolean {
    if (index > array.size - 1)
        return false

    return array[index] == 0
}

fun main(args: Array<String>) {


    jumpingOnClouds(
        arrayOf(
            0, 0, 1, 0, 0, 0, 0, 1, 0, 0
//            0, 0, 1, 0, 0, 1, 0

        )
    )


}
