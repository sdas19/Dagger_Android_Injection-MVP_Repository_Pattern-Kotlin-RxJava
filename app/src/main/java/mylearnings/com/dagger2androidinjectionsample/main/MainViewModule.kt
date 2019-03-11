package mylearnings.com.dagger2androidinjectionsample.main

import dagger.Binds
import dagger.Module

@Module
abstract class MainViewModule {

    @Binds
    internal abstract fun provideMainView(mainActivity: MainActivity): MainContract.View

}