package mylearnings.com.dagger2androidinjectionsample.main

import dagger.Module
import dagger.Provides
import mylearnings.com.dagger2androidinjectionsample.RestApi
import mylearnings.com.dagger2androidinjectionsample.rx.SchedulersFacade

@Module
class MainModule {

    @Provides
    internal fun provideMainPresenter(
        mainView: MainContract.View,
        mainRepository: MainRepository,
        schedulersFacade: SchedulersFacade
    ): MainPresenter {
        return MainPresenter(mainView, mainRepository, schedulersFacade)
    }

    @Provides
    internal fun provideMainRepository(restApi: RestApi): MainRepository {
        return MainRepository(restApi)
    }
}