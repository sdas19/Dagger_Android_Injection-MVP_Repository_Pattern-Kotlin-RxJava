package mylearnings.com.dagger2androidinjectionsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mylearnings.com.dagger2androidinjectionsample.main.MainActivity
import mylearnings.com.dagger2androidinjectionsample.main.MainModule
import mylearnings.com.dagger2androidinjectionsample.main.MainViewModule

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [MainViewModule::class,MainModule::class])
    abstract fun bindMainActivity() : MainActivity

}