package com.bignerdranch.andoid.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity//(tableName = "crime_table")
class Crime(
    var type: Int = A_TYPE,
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false,
    @PrimaryKey val id: UUID = UUID.randomUUID()
) {
    companion object {
        const val A_TYPE = 0
        const val B_TYPE = 1
    }
}