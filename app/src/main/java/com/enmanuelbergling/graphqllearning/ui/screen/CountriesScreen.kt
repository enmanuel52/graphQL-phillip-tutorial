package com.enmanuelbergling.graphqllearning.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enmanuelbergling.graphqllearning.domain.model.SimpleCountry
import com.enmanuelbergling.graphqllearning.ui.model.CountriesState

@Composable
fun CountriesScreen(
    state: CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.countries) { item ->
                    CountryItem(item = item, modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectCountry(item.code) })
                }
            }
        }
    }
}

@Composable
fun CountryItem(item: SimpleCountry, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = item.emoji, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.width(16.dp))

        Column(Modifier.fillMaxWidth()) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = item.capital, style = MaterialTheme.typography.bodyMedium)
        }
    }
}