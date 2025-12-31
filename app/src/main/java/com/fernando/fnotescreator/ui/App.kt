package com.fernando.fnotescreator.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fernando.fnotescreator.ui.screens.addNoteScreen.AddNoteRoute
import com.fernando.fnotescreator.ui.screens.addNoteScreen.AddNoteScreen
import com.fernando.fnotescreator.ui.screens.notesListScreen.NotesListRoute
import com.fernando.fnotescreator.ui.screens.notesListScreen.NotesListScreen
import com.fernando.fnotescreator.ui.theme.FNotesCreatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NotesListRoute,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }) {
        composable<NotesListRoute> {
            NotesListScreen(
                onNavigateToNoteInfoScreen = { noteID ->

                },
                onNavigateToAddNoteScreen = {
                    navController.navigate(AddNoteRoute)
                }
            )
        }
        composable<AddNoteRoute> {
            AddNoteScreen (
                onBackToNotesListScreen = {
                    navController.navigate(NotesListRoute) {
                        popUpTo(AddNoteRoute) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun AppPreview() {
    FNotesCreatorTheme {
        App()
    }
}