package com.kuneosu.newcompose.viewModel

import androidx.lifecycle.ViewModel
import com.kuneosu.newcompose.data.model.BigToon
import com.kuneosu.newcompose.data.model.DataProvider
import com.kuneosu.newcompose.data.model.SmallToon

class MainViewModel : ViewModel() {
    private val _bigToonList: List<BigToon>
        get() = DataProvider.bigToonList
    val bigToonList: List<BigToon>
        get() = _bigToonList

    private val _smallToonList: List<SmallToon>
        get() = DataProvider.smallToonList
    val smallToonList: List<SmallToon>
        get() = _smallToonList

//    private val _gifOption: Boolean
//        get() = true
//    val gifOption: Boolean
//        get() = _gifOption
}