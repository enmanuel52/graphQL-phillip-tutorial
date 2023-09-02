package com.enmanuelbergling.graphqllearning.data.mappers

import com.enmanuelbergling.CountriesQuery
import com.enmanuelbergling.CountryQuery
import com.enmanuelbergling.graphqllearning.domain.model.DetailedCountry
import com.enmanuelbergling.graphqllearning.domain.model.SimpleCountry

fun CountriesQuery.Country.toModel() = SimpleCountry(
    name = name,
    capital = capital.orEmpty(),
    code = code,
    emoji = emoji
)

fun CountryQuery.Country.toModel()  =DetailedCountry(
    name = name,
    capital = capital,
    code = code,
    emoji = emoji,
    currency = currency,
    languages = languages.map { it.name },
    continent = continent.name
)