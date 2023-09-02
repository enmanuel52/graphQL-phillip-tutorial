package com.enmanuelbergling.graphqllearning.domain.model

data class DetailedCountry(
     val name: String,
     val capital: String?,
     val code: String,
     val emoji: String,
     val currency: String?,
     val languages: List<String>,
     val continent: String,
)
