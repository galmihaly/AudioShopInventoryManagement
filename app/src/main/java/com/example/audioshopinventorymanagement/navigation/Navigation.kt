package com.example.audioshopinventorymanagement.navigation

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.audioshopinventorymanagement.storagesscreen.StoragesScreen
import com.example.audioshopinventorymanagement.loginscreen.LoginScreen
import com.example.audioshopinventorymanagement.modifyitemscreen.ModifyItemScreen
import com.example.audioshopinventorymanagement.newitemscreen.NewItemScreen
import com.example.audioshopinventorymanagement.onecategoryscreen.OneCategoryScreen
import com.example.audioshopinventorymanagement.productlistscreen.ProductListScreen
import com.example.audioshopinventorymanagement.startscreen.StartScreen
import com.example.audioshopinventorymanagement.ui.theme.AudioShopInventoryManagementTheme
import com.example.audioshopinventorymanagement.warehousesscreen.WareHousesScreen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun Navigation (
    navigationViewModel: NavigationViewModel = hiltViewModel()
){
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = navigationViewModel.navigationChannel,
        navHostController = navController
    )

    AudioShopInventoryManagementTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = Destination.LoginScreen
            ) {
                composable(destination = Destination.LoginScreen) {
                    LoginScreen()
                }
                composable(destination = Destination.StartScreen) {
                    StartScreen()
                }
                composable(destination = Destination.ProductListScreen) {
                    ProductListScreen()
                }
                composable(destination = Destination.ModifyItemScreen) {
                    ModifyItemScreen()
                }
                composable(destination = Destination.NewItemScreen) {
                    NewItemScreen()
                }
                composable(destination = Destination.StoragesScreen) {
                    StoragesScreen()
                }
                composable(destination = Destination.OneCategoryScreen) {
                    OneCategoryScreen()
                }
                composable(destination = Destination.WareHousesScreen) {
                    WareHousesScreen()
                }
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}