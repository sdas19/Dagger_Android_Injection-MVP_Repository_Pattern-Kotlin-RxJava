package mylearnings.com.dagger2androidinjectionsample.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import mylearnings.com.dagger2androidinjectionsample.App
import javax.inject.Singleton

/* Use AndroidInjectionModule.class if you're not using support library */

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class, ApiModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun apiModule(apiModule: ApiModule): Builder

        fun build(): AppComponent

    }

    fun inject(app: App)
}