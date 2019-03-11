package mylearnings.com.dagger2androidinjectionsample.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import dagger.android.AndroidInjection
import mylearnings.com.dagger2androidinjectionsample.main.model.BreweryModel
import mylearnings.com.dagger2androidinjectionsample.R
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {


    @BindView(R.id.loading_indicator)
    lateinit var loadingIndicator: ProgressBar

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        mainPresenter.loadBrewaryList()
    }


    override fun onStop() {
        super.onStop()
        mainPresenter.stop()
    }

    override fun setLoadingIndicator(visibility: Boolean) {
        loadingIndicator.visibility = if (visibility) View.VISIBLE else View.GONE

    }

    override fun showBrewaryList(brewaryList: List<BreweryModel>) {
        Timber.e(brewaryList.toString())
    }

    override fun displayError(message: String?) {

        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
