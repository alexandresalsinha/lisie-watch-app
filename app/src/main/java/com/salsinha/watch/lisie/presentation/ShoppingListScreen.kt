package com.salsinha.watch.lisie.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items
import androidx.wear.compose.material.rememberScalingLazyListState
import coil.compose.AsyncImage
import com.salsinha.watch.lisie.data.model.ShoppingItem

@Composable
fun ShoppingListScreen(viewModel: ShoppingViewModel) {
    val items by viewModel.shoppingList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val listState = rememberScalingLazyListState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            isLoading -> {
                CircularProgressIndicator()
            }
            error != null -> {
                Text(text = "Error: $error", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
            items.isEmpty() -> {
                Text(text = "No items", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
            else -> {
                ScalingLazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState
                ) {
                    item {
                        Text(
                            text = "Shopping List",
                            style = MaterialTheme.typography.title2,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    items(items) { item ->
                        ShoppingItemRow(item)
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingItemRow(item: ShoppingItem) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!item.imageUrl.isNullOrEmpty()) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = item.name,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (item.brand != null) {
                        Text(
                            text = item.brand,
                            style = MaterialTheme.typography.caption2,
                            color = Color.Gray
                        )
                    }
                }

                Text(
                    text = "x${item.quantity}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            if (expanded && !item.priceList.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                item.priceList.sortedBy { it.price }.forEach { priceItem ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = getStoreName(priceItem.storeId),
                            style = MaterialTheme.typography.caption2,
                            color = Color.LightGray
                        )
                        Text(
                            text = "€${String.format("%.2f", priceItem.price)}",
                            style = MaterialTheme.typography.caption2,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                }
            }
        }
    }
}

fun getStoreName(storeId: Int): String {
    return when (storeId) {
        1 -> "Auchan"
        2 -> "Continente"
        3 -> "Pingo Doce"
        4 -> "Intermarché"
        5 -> "Minipreço"
        7 -> "El Corte Inglés"
        else -> "Store #$storeId"
    }
}
