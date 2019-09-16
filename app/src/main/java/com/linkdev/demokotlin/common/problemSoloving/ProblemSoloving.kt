package com.linkdev.demokotlin.common.problemSoloving


fun hourglassSum(arr: Array<Array<Int>>): Int {

    var resultSum = 0
    val resultArray: MutableList<Int> = mutableListOf()
    var currentColumn = 0


    while (currentColumn <= arr.size - 3) {

        arr.forEachIndexed { row, ints ->
            if (row > arr.size - 3) {
                resultSum = 0
                return@forEachIndexed
            }
            resultSum += arr[row + 1][currentColumn + 1]
            for (i in 0..2) {
                resultSum += arr[row ][i+currentColumn]
            }


            for (j in 0..2) {
                resultSum += arr[row + 2][currentColumn+j]
            }

            resultArray.add(resultSum)
            resultSum = 0

        }
        currentColumn++
    }

    val sortAsc = resultArray.sortedDescending()
    return sortAsc[0]
}


fun main(args: Array<String>) {
    var array: Array<Int> = arrayOf(-9, -9, -9, 1, 1, 1)
    var arr: Array<Array<Int>> = Array(6) { array }

    arr[0] = array

    array = arrayOf(0, -9, 0, 4, 3, 2)
    arr[1] = array

    array = arrayOf(-9, -9, -9, 1, 2, 3)
    arr[2] = array

    array = arrayOf(0, 0, 8, 6, 6, 0)
    arr[3] = array

    array = arrayOf(0, 0, 0, -2, 0, 0)
    arr[4] = array

    array = arrayOf(0, 0, 1, 2, 4, 0)
    arr[5] = array

    hourglassSum(arr)

    println(arr)
}

