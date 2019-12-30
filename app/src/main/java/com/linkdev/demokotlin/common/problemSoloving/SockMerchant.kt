package com.linkdev.demokotlin.common.problemSoloving

// Complete the sockMerchant function below.
fun sockMerchant( ar: Array<Int>): Int {
    var n=ar.size
    var arrayAfterOrdering: Array<Int> = ar.sortedArray()
    var firstElement = arrayAfterOrdering[0]
    var curentIteration = 0
    var numbersOfPairs = 0
    arrayAfterOrdering.forEachIndexed { index, value ->

        if (firstElement != value && curentIteration != 0) {
            firstElement = value
            if (curentIteration % 2 == 0) {
                numbersOfPairs += curentIteration / 2
            } else {
                numbersOfPairs += (curentIteration - 1) / 2
            }
            curentIteration = 1
        } else if (n-1 == index) {
            curentIteration++
            if (curentIteration % 2 == 0) {
                numbersOfPairs += curentIteration / 2
            } else {
                numbersOfPairs += (curentIteration - 1) / 2
            }
        } else {
            curentIteration++
        }

    }
    return numbersOfPairs
}

fun main(args: Array<String>) {
    sockMerchant( arrayOf(6 ,5 ,2, 3, 5 ,2 ,2 ,1, 1 ,5 ,1 ,3 ,3, 3, 5))
}
