package com.bignerdranch.andoid.criminalintent.database

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.andoid.criminalintent.Crime
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "crimedatabase"
class CrimeRepository private constructor(/*private val crimeDao: CrimeDao,*/ context: Context){


    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val crimeDao = database.crimeDao()
//    val allWords: kotlinx.coroutines.flow.Flow<List<Crime>> = crimeDao.getAlphabetizedCrimes()
//
//    fun getCrimes(): kotlinx.coroutines.flow.Flow<List<Crime>> = crimeDao.getCrimes()
    fun getCrimes(): LiveData<List<Crime>> =  crimeDao.getCrimes()
    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Crime) {
        crimeDao.insert(word)
    }

    companion object{
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}