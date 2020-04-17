package yovi.putra.github.core

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import yovi.putra.github.BuildConfig
import yovi.putra.github.di.appModules

class AppContext : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO
            )
            androidContext(this@AppContext)
            modules(appModules)
        }
    }
}