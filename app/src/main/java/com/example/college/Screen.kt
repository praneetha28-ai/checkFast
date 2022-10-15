package com.example.college

sealed class Screen(val route:String){
    object WelcomeScreen : Screen("welcome_screen")
    object OptionsScreen : Screen("options_screen")
    object Attendance : Screen("Attendance")
    object Sgpa : Screen("Sgpa")
    object Bill : Screen("Bill")
}
