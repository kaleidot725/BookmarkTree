package jp.albites.btree

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings
import jp.albites.btree.model.repository.ThemeRepository
import jp.albites.btree.view.screen.theme.ThemeScreenModel
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.prefs.Preferences

actual val osModule : Module = module {
    factory {
        val delegate: Preferences = Preferences.userRoot()
        val settings: ObservableSettings = PreferencesSettings(delegate)
        ThemeRepository(settings = settings)
    }
}