package com.example.wazitoecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.ui.theme.screens.home.HomeScreen
import com.example.wazitoecommerce.ui.theme.screens.login.LoginScreen
import com.example.wazitoecommerce.ui.theme.screens.complaints.AddComplaintsScreen
import com.example.wazitoecommerce.ui.theme.screens.complaints.ViewComplaintsScreen
import com.example.wazitoecommerce.ui.theme.screens.phome.PHomeScreen
import com.example.wazitoecommerce.ui.theme.screens.signup.SignupScreen
import com.example.wazitoecommerce.ui.theme.screens.splash.SplashScreen
import com.example.wazitoecommerce.ui.theme.startup.StartupScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
    startDestination:String = SPLASH_URL
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL){
            SignupScreen(navController = navController)
        }
        composable(HOME_URL){
            HomeScreen(navController = navController)
        }
        composable(ADD_PRODUCTS_URL){
            AddComplaintsScreen(navController = navController)
        }
        composable(VIEW_PRODUCTS_URL){
            ViewComplaintsScreen(navController = navController)
        }

        composable(SPLASH_URL){
            SplashScreen(navController = navController)
        }

        composable(STARTUP_URL){
            StartupScreen(navController = navController)
        }

        composable(P_HOME_URL){
            PHomeScreen(navController = navController)
        }
    }
}