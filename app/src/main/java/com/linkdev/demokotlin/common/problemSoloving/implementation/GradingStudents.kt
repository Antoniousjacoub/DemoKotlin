package com.linkdev.demokotlin.common.problemSoloving.implementation

fun gradingStudents(grades: Array<Int>): Array<Int> {

    // Complete this function
    for (i in 0 until grades.size) {
        if (grades[i] >= 38) {
            if ( (5 - grades[i] % 5)  < 3)
                grades[i] = grades[i] + (5 - grades[i] % 5)
        }
    }
    return grades
}

fun main(args: Array<String>) {
    gradingStudents(
        arrayOf(
            73,
            67,
            38,
            33
        )
    )

}
