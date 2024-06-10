package com.catches.securities_api.application.enum

enum class GradeGroup(val grades: Set<String>) {
    AAA(setOf("AAA")),
    AA(setOf("AA+", "AA", "AA-")),
    A(setOf("A+", "A", "A-")),
    BBB(setOf("BBB+", "BBB", "BBB-")),
    OTHER(emptySet());

    companion object {
        fun fromGrade(grade: String): GradeGroup {
            return values().find { it.grades.contains(grade) } ?: OTHER
        }

        fun convertGradeRange(gradeGroup: GradeGroup): String {
            return when (gradeGroup) {
                AAA -> "AAA"
                AA -> "AA+ ~ AA-"
                A -> "A+ ~ A-"
                BBB -> "BBB+ ~ BBB-"
                OTHER -> "이하 등급"
            }
        }
    }
}