package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.usecase.GetCoinByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    val getCoinByIdUseCase: GetCoinByIdUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoin(it)
        }
    }

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    private fun getCoin(coinId: String) {
        getCoinByIdUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> _state.value =
                    CoinDetailState(coin = result.data)

                is Resource.Failure -> _state.value =
                    CoinDetailState(error = result.errorMsg ?: "An error occurred")

                is Resource.Loading -> _state.value = CoinDetailState(isLoading = true)

            }

        }.launchIn(viewModelScope)
    }
}