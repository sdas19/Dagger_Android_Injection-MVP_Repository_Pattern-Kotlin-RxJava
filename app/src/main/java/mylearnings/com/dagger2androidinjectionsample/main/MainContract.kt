package mylearnings.com.dagger2androidinjectionsample.main

import mylearnings.com.dagger2androidinjectionsample.main.model.BreweryModel

interface MainContract {

    interface View {
        fun setLoadingIndicator(visibility: Boolean)

        fun showBrewaryList(brewaryList: List<BreweryModel>)

        fun displayError(message: String?)
    }

    interface Presenter {
        fun loadBrewaryList()
    }
}