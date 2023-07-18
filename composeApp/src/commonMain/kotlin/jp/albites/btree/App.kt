package jp.albites.btree

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.skeptick.libres.compose.painterResource
import jp.albites.btree.explorer.filetree.File
import jp.albites.btree.theme.AppTheme
import view.components.explorer.Explorer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App(openUrl: (String) -> Unit) = AppTheme {
    val targetFile: File by remember { mutableStateOf(fileTree) }
    var selectedFile: File by remember { mutableStateOf(targetFile) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Default.Delete, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Default.ModeEdit,
                            contentDescription = "Localized description",
                        )
                    }
                    if (!selectedFile.isDirectory) {
                        IconButton(onClick = { openUrl(selectedFile.url) }) {
                            Icon(
                                painter = MainRes.image.browser.painterResource(),
                                contentDescription = "Localized description",
                            )
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }
            )
        }
    ) {
        Explorer(
            title = "BTree",
            targetFile = targetFile,
            selectedFile = selectedFile,
            onClickHome = {},
            onClickFile = { selectedFile = it },
            modifier = Modifier.fillMaxSize()
        )
    }
}

internal expect fun openUrl(url: String?)

private val fileTree = File(
    isDirectory = true,
    listFiles = listOf(
        File(
            isDirectory = true,
            listFiles = listOf(
                File(
                    isDirectory = false,
                    listFiles = listOf(),
                    name = "Google Store",
                    url = "https://store.google.com"
                ),
                File(
                    isDirectory = false,
                    listFiles = listOf(),
                    name = "Google Search",
                    url = "https://google.com"
                )
            ),
            name = "GOOGLE"
        ),
        File(
            isDirectory = true,
            listFiles = listOf(
                File(
                    isDirectory = false,
                    listFiles = listOf(),
                    name = "Apple Store",
                    url = "https://www.apple.com/jp/store"
                ),
                File(
                    isDirectory = false,
                    listFiles = listOf(),
                    name = "SwiftUI",
                    url = "https://developer.apple.com/jp/xcode/swiftui/"
                )
            ),
            name = "APPLE"
        )
    ),
    name = "ROOT"
)