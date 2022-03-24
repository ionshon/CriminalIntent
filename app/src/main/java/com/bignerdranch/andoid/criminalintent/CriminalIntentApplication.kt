package com.bignerdranch.andoid.criminalintent

import android.app.Application
import com.bignerdranch.andoid.criminalintent.database.CrimeRepository

class CriminalIntentApplication: Application() {

    // No need to cancel this scope as it'll be torn down with the process
 //   val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
//    val database by lazy { CrimeDatabase.getDatabase(this, applicationScope) }
//    val repository by lazy { CrimeRepository(database.crimeDao(), this) }

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }

}