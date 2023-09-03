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
    private val getCountriesUC: GetCountriesUC,
    private val getCountryByIdUC: GetCountryByIdUC,
) : ViewModel() {

    private val _countriesState = MutableStateFlow(CountriesState())
    val countriesState get() = _countriesState.asStateFlow()

    init {
        getAllCountries()
    }

    fun selectCountry(code: String) = viewModelScope.launch {
        _countriesState.update { it.copy(isLoading = true) }
        val detailedCountry = getCountryByIdUC(code)
        _countriesState.update { it.copy(selectedCountry = detailedCountry, isLoading = false) }
    }

    fun dismissCountryDialog() {
        _countriesState.update { it.copy(selectedCountry = null) }
    }

    private fun getAllCountries() = viewModelScope.launch {
        _countriesState.update { it.copy(isLoading = true) }
        val newData = getCountriesUC()
        _countriesState.update { it.copy(isLoading = false, countries = newData) }
    }
}