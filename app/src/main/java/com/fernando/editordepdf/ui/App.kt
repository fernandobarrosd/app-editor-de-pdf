package com.fernando.editordepdf.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fernando.editordepdf.ui.screens.pdfsListScreen.PdfsListRoute
import com.fernando.editordepdf.ui.screens.pdfsListScreen.PdfsListScreen
import com.fernando.editordepdf.ui.theme.EditorDePDFTheme
import com.fernando.editordepdf.ui.theme.Red500
import com.fernando.editordepdf.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val title = backStackEntry?.destination?.let { destination ->
        when {
            destination.hasRoute<PdfsListRoute>() -> "Listagem dos PDFs"
            else -> ""
        }
    } ?: ""

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.padding(bottom = 16.dp),
        title = {
            Text(
                text = title,
                color = Color.White,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun App() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Red500,
                shape = RoundedCornerShape(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "Add icon"
                )
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = PdfsListRoute) {
            composable<PdfsListRoute> {
                PdfsListScreen(
                    paddingValues = paddingValues,
                    onNavigateToPdfInfoScreen = { pdfInfoId ->

                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AppPreview() {
    EditorDePDFTheme {
        App()
    }
}