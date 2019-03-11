package mylearnings.com.dagger2androidinjectionsample.main


import io.reactivex.Single
import mylearnings.com.dagger2androidinjectionsample.RestApi
import mylearnings.com.dagger2androidinjectionsample.main.model.BreweryModel
import javax.inject.Inject

class MainRepository @Inject constructor(val restApi: RestApi) {

    fun getBrewaryList(): Single<List<BreweryModel>> {
        return restApi.getBreweries()
    }

}