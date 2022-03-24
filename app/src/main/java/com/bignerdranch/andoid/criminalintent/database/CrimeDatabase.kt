package com.bignerdranch.andoid.criminalintent.database

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bignerdranch.andoid.criminalintent.Crime
import com.bignerdranch.andoid.criminalintent.Crime.Companion.A_TYPE
import com.bignerdranch.andoid.criminalintent.Crime.Companion.B_TYPE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Crime::class], version = 1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase: RoomDatabase() {

    abstract fun crimeDao(): CrimeDao
/*
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE : CrimeDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): CrimeDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: kotlin.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CrimeDatabase::class.java,
                    "crimedatabase",
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }

        }
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var crimeDao = database.crimeDao()

                    // Delete all content here.
                    crimeDao.deleteAll()
    Log.d("crimeDatabase: ", "as")
                    // Add sample words.
                      var crime = Crime(A_TYPE, "title")
                    crimeDao.insert(crime)
                    crime = Crime(B_TYPE, "World!")
                    crimeDao.insert(crime)

                    // TODO: Add your own Crimes!
                    crime = Crime(A_TYPE, "TODO!")
                    crimeDao.insert(crime)
                }
            }
        }
    }*/
}

