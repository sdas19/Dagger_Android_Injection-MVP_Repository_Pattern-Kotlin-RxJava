package mylearnings.com.dagger2androidinjectionsample.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V> protected constructor(protected val view: V) {

    private val disposables = CompositeDisposable()

    enum class RequestState {
        IDLE,
        LOADING,
        COMPLETE,
        ERROR
    }

    /**
     * Contains common setup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    fun start() {}

    /**
     * Contains common cleanup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    fun stop() {
        disposables.clear()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
