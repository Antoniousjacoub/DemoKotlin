package com.linkdev.demokotlin.common.problemSoloving.warm_up

import kotlin.math.absoluteValue


fun diagonalDifference(arr: Array<Array<Int>>): Int {
    // Write your code here
    var x = 0
    var y = 0
    arr.forEachIndexed { index, element ->
        x += arr[index][index]
        y += arr[index][((element.size)-1)-index]

    }
    return (x-y).absoluteValue

}

fun main(args: Array<String>) {
    diagonalDifference(arrayOf(arrayOf(1 ,2 ,3), arrayOf(4 ,5 ,6), arrayOf(9 ,8 ,9)))

}