package com.kuneosu.newcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuneosu.newcompose.data.room.ToonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {
    private val _searchResult = MutableStateFlow<List<String>>(emptyList())
    val searchResult: StateFlow<List<String>> = _searchResult

    private fun setSearchResult(bigToons: List<String>, smallToons: List<String>) {
        val result = mutableListOf<String>()
        bigToons.forEach {
            result.add(it)
        }
        smallToons.forEach {
            result.add(it)
        }
        _searchResult.value = result
    }

    fun searchToons(query: String, db: ToonDatabase) {
        viewModelScope.launch {
            val bigToonResult = withContext(Dispatchers.IO) {
                db.toonDao().searchBigToon(query)
            }
            val smallToonResult = withContext(Dispatchers.IO) {
                db.toonDao().searchSmallToon(query)
            }
            setSearchResult(bigToonResult, smallToonResult)
        }
    }
}
