package com.enmanuelbergling.graphqllearning.domain.usecase

import com.enmanuelbergling.graphqllearning.domain.CountryClient
import javax.inject.Inject

class GetCountriesUC  @Inject constructor(
    private val client: CountryClient
) {
    suspend operator fun invoke() = client.getCountries()
}