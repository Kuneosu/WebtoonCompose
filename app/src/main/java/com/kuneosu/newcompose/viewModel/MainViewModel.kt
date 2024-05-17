package com.kuneosu.newcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuneosu.newcompose.data.model.BigToon
import com.kuneosu.newcompose.data.model.DataProvider
import com.kuneosu.newcompose.data.model.SmallToon
import com.kuneosu.newcompose.ui.theme.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _bigToonList: List<BigToon>
        get() = DataProvider.bigToonList
    val bigToonList: List<BigToon>
        get() = _bigToonList

    private val _smallToonList: List<SmallToon>
        get() = DataProvider.smallToonList
    val smallToonList: List<SmallToon>
        get() = _smallToonList


    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            _themeMode.value = mode
        }
    }
}


//    private val _gifOption: Boolean
//        get() = true
//    val gifOption: Boolean
//        get() = _gifOption
