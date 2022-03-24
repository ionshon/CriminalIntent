package com.bignerdranch.andoid.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.andoid.criminalintent.Crime
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CrimeDao {

    @Query("SELECT * FROM crime")
    fun getCrimes(): LiveData<List<Crime>>
   // fun getAlphabetizedCrimes(): Flow<List<Crime>>

//    @Query("SELECT * FROM crime")
//    fun getCrime(): List<Crime>
   // fun getCrimes(): Flow<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID) : LiveData<Crime?>
 //   fun getCrime(id: UUID) : Crime?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(crime: Crime)

    @Query("DELETE FROM crime")
    suspend fun deleteAll()
}