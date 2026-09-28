package org.ucb.appp1.earthquakes.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.earthquakes.domain.model.EarthquakeModel
import org.ucb.appp1.earthquakes.presentation.viewmodel.EarthquakesEvent
import org.ucb.appp1.earthquakes.presentation.viewmodel.EarthquakesViewModel
import kotlin.time.Instant

@Composable
fun EarthquakesScreen(viewModel: EarthquakesViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState().value

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().systemBarsPadding()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Terremotos USGS",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Eventos recientes de magnitud 5 o superior",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            when {
                state.isLoading -> LoadingContent()
                state.error != null -> ErrorContent(
                    message = state.error,
                    onRetry = { viewModel.emitEvent(EarthquakesEvent.Retry) }
                )
                state.earthquakes.isEmpty() -> EmptyContent()
                else -> LazyColumn(
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.earthquakes) { earthquake ->
                        EarthquakeCard(earthquake)
                    }
                }
            }
        }
    }
}

@Composable
private fun EarthquakeCard(earthquake: EarthquakeModel) {
    val uriHandler = LocalUriHandler.current
    val magnitudeColor = when {
        earthquake.magnitude >= 7.0 -> Color(0xFFB3261E)
        earthquake.magnitude >= 6.0 -> Color(0xFFE65100)
        else -> Color(0xFFF9A825)
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(color = magnitudeColor, shape = RoundedCornerShape(12.dp)) {
                    Text(
                        text = "M ${earthquake.magnitude}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
                Text(
                    text = earthquake.place,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalDivider()

            DataRow("Fecha y hora", formatTimestamp(earthquake.occurredAtEpochMillis))
            DataRow("Latitud", earthquake.latitude.toString())
            DataRow("Longitud", earthquake.longitude.toString())
            DataRow("Profundidad", "${earthquake.depthKilometers} km")

            Button(
                onClick = { uriHandler.openUri(earthquake.eventUrl) },
                enabled = earthquake.eventUrl.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver evento en USGS")
            }
        }
    }
}

@Composable
private fun DataRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(0.42f)
        )
        Text(text = value, modifier = Modifier.weight(0.58f))
    }
}

private fun formatTimestamp(epochMillis: Long): String =
    if (epochMillis > 0L) {
        Instant.fromEpochMilliseconds(epochMillis).toString().replace("T", " ").removeSuffix("Z") + " UTC"
    } else {
        "Fecha no disponible"
    }

@Composable
private fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("No se encontraron terremotos")
    }
}

@Composable
private fun ErrorContent(message: String, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Text(message)
        Button(onClick = onRetry) {
            Text("Reintentar")
        }
    }
}
