package com.kuneosu.newcompose.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kuneosu.newcompose.data.model.DataProvider
import com.kuneosu.newcompose.data.room.BigToon
import com.kuneosu.newcompose.data.room.SmallToon
import com.kuneosu.newcompose.ui.theme.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun BooleanArray.toStringRepresentation(): String {
    return this.joinToString(separator = ",")
}

fun String.toBooleanArray(): BooleanArray {
    return this.split(",").map { it.toBoolean() }.toBooleanArray()
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences =
        application.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)


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


    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode

    private val _gifOption = MutableStateFlow(false)
    val gifOption: StateFlow<Boolean> = _gifOption

    init {
        // 초기화 시 저장된 테마 모드를 불러옵니다.
        val savedThemeMode = preferences.getString("theme_mode", ThemeMode.SYSTEM.name)
        _themeMode.value = ThemeMode.valueOf(savedThemeMode ?: ThemeMode.SYSTEM.name)

        val savedDisplayChoice = preferences.getString("display_choice", null)
        if (savedDisplayChoice != null) {
            _displayChoice.value = savedDisplayChoice.toBooleanArray()
        }

    }

    fun setDisplayChoice(choice: BooleanArray) {
        viewModelScope.launch {
            _displayChoice.value = choice
            preferences.edit().putString("display_choice", choice.toStringRepresentation()).apply()
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            _themeMode.value = mode
            preferences.edit().putString("theme_mode", mode.name).apply()
        }
    }

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
