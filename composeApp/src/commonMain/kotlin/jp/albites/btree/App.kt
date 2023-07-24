package jp.albites.btree

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.Navigator
import jp.albites.btree.model.domain.Theme
import jp.albites.btree.model.repository.ThemeRepository
import jp.albites.btree.view.screen.home.HomeScreen
import jp.albites.btree.view.theme.AppTheme
import jp.albites.btree.view.theme.DarkColorScheme
import jp.albites.btree.view.theme.LightColorScheme
import kotlinx.coroutines.flow.Flow
import org.koin.mp.KoinPlatform

@Composable
internal fun App(openUrl: (String) -> Unit) {
    val theme by getThemeFlow().collectAsState(Theme.SYSTEM)
    AppTheme(getColorScheme(theme)) {
        Navigator(
            HomeScreen(openUrl = openUrl)
        )
    }
}

@Composable
private fun getThemeFlow(): Flow<Theme> {
    val koin = KoinPlatform.getKoin()
    return koin.get<ThemeRepository>().themeFlow
}

@Composable
private fun getColorScheme(theme: Theme): ColorScheme {
    return when (theme) {
        Theme.DARK -> DarkColorScheme
        Theme.LIGHT -> LightColorScheme
        Theme.SYSTEM -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    }
}

internal expect fun openUrl(url: String?)