package com.enmanuelbergling.graphqllearning.data.graphql

import com.apollographql.apollo3.ApolloClient
import com.enmanuelbergling.CountriesQuery
import com.enmanuelbergling.CountryQuery
import com.enmanuelbergling.graphqllearning.data.mappers.toModel
import com.enmanuelbergling.graphqllearning.domain.CountryClient
import com.enmanuelbergling.graphqllearning.domain.model.DetailedCountry
import com.enmanuelbergling.graphqllearning.domain.model.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient{

    override suspend fun getCountries(): List<SimpleCountry> =
        apolloClient.query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toModel() }
            .orEmpty()

    override suspend fun getCountry(code: String): DetailedCountry? =
        apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toModel()
}