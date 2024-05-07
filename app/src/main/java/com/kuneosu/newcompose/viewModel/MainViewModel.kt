package com.kuneosu.newcompose.viewModel

import androidx.lifecycle.ViewModel
import com.kuneosu.newcompose.data.model.DataProvider
import com.kuneosu.newcompose.data.model.Toon

class MainViewModel : ViewModel() {
    private val _toonList1: List<Toon>
        get() = DataProvider.toonList
    val toonList1: List<Toon>
        get() = _toonList1

    private val _toonList2: List<Toon>
        get() = DataProvider.toonList2
    val toonList2: List<Toon>
        get() = _toonList2

    private val _gifOption: Boolean
        get() = true
    val gifOption: Boolean
        get() = _gifOption

}