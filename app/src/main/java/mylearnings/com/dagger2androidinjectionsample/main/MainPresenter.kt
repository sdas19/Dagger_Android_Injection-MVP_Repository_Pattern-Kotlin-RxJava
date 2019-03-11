package mylearnings.com.dagger2androidinjectionsample.main

import android.annotation.SuppressLint
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import mylearnings.com.dagger2androidinjectionsample.mvp.BasePresenter
import mylearnings.com.dagger2androidinjectionsample.rx.SchedulersFacade
import timber.log.Timber

class MainPresenter(
    val mainView: MainContract.View,
    val mainRepository: MainRepository,
    val schedulersFacade: SchedulersFacade
)

    : BasePresenter<MainContract.View>(mainView), MainContract.Presenter {

    private val requestStateObserver = BehaviorRelay.createDefault(RequestState.IDLE)

    init {
        observeRequestState()
    }

    override fun loadBrewaryList() {

        addDisposable(
            mainRepository.getBrewaryList()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe { publishRequestState(RequestState.LOADING) }
                .doOnSuccess { publishRequestState(RequestState.COMPLETE) }
                .doOnError { publishRequestState(RequestState.ERROR) }
                .subscribe(
                    { brewaryList -> brewaryList?.let { view.showBrewaryList(brewaryList) } },
                    { error -> view.displayError(error.message) }
                )
        )
    }

    private fun publishRequestState(requestState: RequestState) {
        addDisposable(
            Observable.just(requestState)
                .observeOn(schedulersFacade.ui())
                .subscribe(requestStateObserver)
        )
    }

    @SuppressLint("CheckResult")
    private fun observeRequestState() {
        requestStateObserver.subscribe(
            { requestState ->
                when (requestState) {
                    RequestState.IDLE -> {
                    }
                    RequestState.LOADING -> {
                        view.setLoadingIndicator(true)
                    }
                    RequestState.COMPLETE -> view.setLoadingIndicator(false)
                    RequestState.ERROR -> view.setLoadingIndicator(false)
                }
            },
            { error ->
                Timber.e(error)
            }
        )
    }
}