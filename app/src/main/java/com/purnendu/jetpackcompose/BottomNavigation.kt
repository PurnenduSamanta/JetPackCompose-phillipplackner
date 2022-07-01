package com.purnendu.jetpackcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlin.collections.List


class BottomNavigationWithBadges : androidx.activity.ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem("Home", "home", Icons.Default.Home),
                            BottomNavItem("Notification", "notification", Icons.Default.Notifications,10),
                            BottomNavItem("Setting", "setting", Icons.Default.Settings)
                        ),
                        navController = navController,
                        onItemClick = { navController.navigate(it.route) }
                    )
                }
            ) {
                Navigation(navController = navController)
                Spacer(modifier = Modifier.padding(it))
            }

        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home")
    {
        composable("home")
        {
            HomeScreen()
        }

        composable("notification")
        {
            NotificationScreen()
        }

        composable("setting")
        {
            SettingScreen()
        }
    }

}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {

        items.forEach {

            val selected = it.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(it) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (it.badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    Text(text = it.badgeCount.toString(), color =Color.Red)
                                },
                            ) {

                                Icon(imageVector = it.icon, contentDescription = "")

                            }
                        } else {
                            Icon(imageVector = it.icon, contentDescription = "")
                        }

                        if (selected)
                            Text(
                                text = it.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )

                    }
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray
            )
        }
    }
}


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun NotificationScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Notification Screen")
    }
}

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Setting Screen")
    }
}

class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)








