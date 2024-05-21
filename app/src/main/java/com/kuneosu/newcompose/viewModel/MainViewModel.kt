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

    private val _reverseBigToonList: List<BigToon>
        get() = DataProvider.reverseBigToonList

    val reverseBigToonList: List<BigToon>
        get() = _reverseBigToonList

    private val _shuffleBigToonList: List<BigToon>
        get() = DataProvider.shuffleBigToonList

    val shuffleBigToonList: List<BigToon>
        get() = _shuffleBigToonList

    private val _reverseSmallToonList: List<SmallToon>
        get() = DataProvider.reverseSmallToonList

    val reverseSmallToonList: List<SmallToon>
        get() = _reverseSmallToonList

    private val _shuffleSmallToonList: List<SmallToon>
        get() = DataProvider.shuffleSmallToonList

    val shuffleSmallToonList: List<SmallToon>
        get() = _shuffleSmallToonList


    private val _displayChoice = MutableStateFlow(booleanArrayOf(false, false, true))
    val displayChoice: StateFlow<BooleanArray> = _displayChoice

    fun setDisplayChoice(choice: BooleanArray) {
        viewModelScope.launch {
            _displayChoice.value = choice
        }
    }


    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            _themeMode.value = mode
        }
    }

    private val _gifOption = MutableStateFlow(false)
    val gifOption: StateFlow<Boolean> = _gifOption

    fun setGifOption(option: Boolean) {
        viewModelScope.launch {
            _gifOption.value = option
        }
    }

    private val _wifiOption = MutableStateFlow(false)
    val wifiOption: StateFlow<Boolean> = _wifiOption

    fun setWifiOption(option: Boolean) {
        viewModelScope.launch {
            _wifiOption.value = option
        }
    }
}


//    private val _gifOption: Boolean
//        get() = true
//    val gifOption: Boolean
//        get() = _gifOption
