package mylearnings.com.dagger2androidinjectionsample

import io.reactivex.Single
import mylearnings.com.dagger2androidinjectionsample.main.model.BreweryModel
import retrofit2.http.GET

interface RestApi {

    @GET("breweries")
    fun getBreweries(): Single<List<BreweryModel>>
}