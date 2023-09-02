package com.enmanuelbergling.graphqllearning.domain

import com.enmanuelbergling.CountryQuery
import com.enmanuelbergling.graphqllearning.domain.model.DetailedCountry
import com.enmanuelbergling.graphqllearning.domain.model.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>

    suspend fun getCountry(code: String): DetailedCountry?
}