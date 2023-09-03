package com.enmanuelbergling.graphqllearning.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enmanuelbergling.graphqllearning.domain.usecase.GetCountriesUC
import com.enmanuelbergling.graphqllearning.domain.usecase.GetCountryByIdUC
import com.enmanuelbergling.graphqllearning.ui.model.CountriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesVM @Inject constructor(
    getCountriesUC: GetCountriesUC,
    private val getCountryByIdUC: GetCountryByIdUC,
) : ViewModel() {

    private val _countriesState = MutableStateFlow(CountriesState())
    val countriesState get() = _countriesState.asStateFlow()

    init {
        viewModelScope.launch {
            _countriesState.update { it.copy(isLoading = true) }

            _countriesState.update {
                it.copy(isLoading = false, countries = getCountriesUC())
            }
        }
    }

    fun selectCountry(code: String) = viewModelScope.launch {
        _countriesState.update { it.copy(selectedCountry = getCountryByIdUC(code)) }
    }

    fun dismissCountryDialog() {
        _countriesState.update { it.copy(selectedCountry = null) }
    }
}