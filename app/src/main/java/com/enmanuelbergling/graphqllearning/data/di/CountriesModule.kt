package com.enmanuelbergling.graphqllearning.data.di

import com.apollographql.apollo3.ApolloClient
import com.enmanuelbergling.graphqllearning.data.graphql.ApolloCountryClient
import com.enmanuelbergling.graphqllearning.domain.CountryClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountriesModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient =
        ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    
    @Provides
        @Singleton
        fun provideCountryClient(apolloClient: ApolloClient): CountryClient = ApolloCountryClient(apolloClient)
}