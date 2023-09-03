package com.enmanuelbergling.graphqllearning.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.window.Dialog
import com.enmanuelbergling.graphqllearning.domain.model.DetailedCountry
import com.enmanuelbergling.graphqllearning.domain.model.SimpleCountry
import com.enmanuelbergling.graphqllearning.ui.model.CountriesState

@Composable
fun CountriesScreen(
    state: CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissDialog: () -> Unit
) {

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
    if (state.isLoading) {
        Dialog(onDismissRequest = { }) {
            CircularProgressIndicator(
                Modifier
                    .background(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.shapes.medium
                    )
                    .padding(12.dp)
            )
        }
    }

    if (state.selectedCountry != null) {
        CountryDialog(country = state.selectedCountry, onDismiss = onDismissDialog)
    }
}

@Composable
fun CountryItem(item: SimpleCountry, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = item.emoji, style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.width(16.dp))

        Column(Modifier.fillMaxWidth()) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = item.capital, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = modifier
                .background(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.shapes.medium
                )
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = country.emoji, style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = country.name, style = MaterialTheme.typography.titleMedium)
            }

            Text(text = "Continent: " + country.continent)
            Text(text = "Currency: " + country.currency)
            Text(text = "Capital: " + country.capital)


            //joinToString() is another way
            val languageString = country.languages.let { list ->
                buildString {
                    list.forEachIndexed { index, language ->
                        if (index == 0) append(language)
                        else if (index != country.languages.size - 1) {
                            append(", $language")
                        } else append(" and $language.")
                    }
                }
            }


            Text(text = "Language(s): $languageString")
        }
    }
}