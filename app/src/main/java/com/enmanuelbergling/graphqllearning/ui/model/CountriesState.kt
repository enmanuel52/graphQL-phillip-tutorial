package com.enmanuelbergling.graphqllearning.ui.model

import com.enmanuelbergling.graphqllearning.domain.model.DetailedCountry
import com.enmanuelbergling.graphqllearning.domain.model.SimpleCountry

data class CountriesState(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCountry: DetailedCountry? = null
)
