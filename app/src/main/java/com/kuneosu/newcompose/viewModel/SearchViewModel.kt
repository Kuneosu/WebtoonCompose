package com.kuneosu.newcompose.viewModel

import androidx.lifecycle.ViewModel


class SearchViewModel : ViewModel() {
    private var _searchResult: MutableList<String> = mutableListOf()
    val searchResult: List<String>
        get() = _searchResult

    fun setSearchResult(bigToons: List<String>, smallToons: List<String>) {
        val result = mutableListOf<String>()
        bigToons.forEach {
            result.add(it)
        }
        smallToons.forEach {
            result.add(it)
        }
        _searchResult = result

    }

}