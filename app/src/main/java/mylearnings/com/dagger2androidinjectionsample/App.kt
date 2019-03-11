package mylearnings.com.dagger2androidinjectionsample

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import mylearnings.com.dagger2androidinjectionsample.di.ApiModule
import mylearnings.com.dagger2androidinjectionsample.di.AppModule
import mylearnings.com.dagger2androidinjectionsample.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        DaggerAppComponent.builder()
            .application(this)
            .apiModule(ApiModule("https://api.openbrewerydb.org/"))
            .build()
            .inject(this)

    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}