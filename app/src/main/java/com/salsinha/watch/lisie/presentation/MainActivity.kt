package com.salsinha.watch.lisie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.Composable
import androidx.wear.tooling.preview.devices.WearDevices
import androidx.compose.ui.tooling.preview.Preview
import com.salsinha.watch.lisie.presentation.theme.LisiewatchappTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ShoppingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        
        setTheme(android.R.style.Theme_DeviceDefault)
        
        viewModel.fetchShoppingList("9ff8224f-17cf-49fb-b555-05779a13eb40")

        setContent {
            WearApp(viewModel)
        }
    }
}

@Composable
fun WearApp(viewModel: ShoppingViewModel) {
    LisiewatchappTheme {
        ShoppingListScreen(viewModel)
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    // Preview logic would need a mock ViewModel
}