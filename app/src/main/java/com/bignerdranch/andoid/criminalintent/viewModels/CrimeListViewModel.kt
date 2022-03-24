package com.bignerdranch.andoid.criminalintent.viewModels

import androidx.lifecycle.*
import com.bignerdranch.andoid.criminalintent.Crime
import com.bignerdranch.andoid.criminalintent.database.CrimeRepository
import kotlinx.coroutines.launch

class CrimeListViewModel/*(private val repository: CrimeRepository)*/ : ViewModel(){

   /*  internal val crimes = mutableListOf<Crime>()

    init {
        for (i in 0 until 100) {
            val crime = Crime()
            crime.type = i % 2
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
           // crime.type = i % 2
            crimes += crime
        }
    }*/


    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes() //.asLiveData()
    fun insert(word: Crime) =  viewModelScope.launch {
        crimeRepository.insert(word)
    }
  //  val crimeListLiveData: LiveData<List<Crime>> = repository.getCrimes().asLiveData()

    /*fun insert(word: Crime) =  viewModelScope.launch {
        repository.insert(word)
    }*/
}


/*class CrimeListViewModelFactory(private val repository: CrimeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrimeListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CrimeListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
