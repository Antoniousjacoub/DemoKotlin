package com.linkdev.demokotlin.common.problemSoloving

import java.util.*

fun rotLeft(a: Array<Int>, d: Int): Array<Int> {

    leftRotate(a, a.size, d)
    return a
}

fun leftRotatebyOne(arr: Array<Int>, n: Int) {
    val temp = arr[0]
    var i: Int
    i = 0
    while (i < n - 1) {
        arr[i] = arr[i + 1]
        i++
    }

    arr[i] = temp
}

/*Function to left rotate arr[] of size n by d*/
fun leftRotate(arr: Array<Int>, d: Int, n: Int) {
    for (i in 0 until d)
        leftRotatebyOne(arr, n)
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val nd = scan.nextLine().split(" ")

    val n = nd[0].trim().toInt()

    val d = nd[1].trim().toInt()

    val a = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = rotLeft(a, d)

    println(result.joinToString(" "))
}
