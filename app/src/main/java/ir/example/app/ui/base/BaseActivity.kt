package ir.example.app.ui.base

import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ir.example.app.di.viewmodel.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity<S : BaseViewState, VM : BaseViewModel<S>> : DaggerAppCompatActivity() {

    @Inject
    @JvmField
    var viewModelFactory: ViewModelFactory? = null
    val compositDesposable: CompositeDisposable = CompositeDisposable()


    protected lateinit var viewModel: VM

    override fun onStart() {
        super.onStart()
        startObserving()
    }

    override fun onDestroy() {
        compositDesposable.clear()
        super.onDestroy()
    }


    fun createViewModel(clazz: Class<VM>) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(clazz)
    }


    fun showToast(@StringRes string: Int) {
        if (string != 0)
            Toast.makeText(applicationContext, getString(string), Toast.LENGTH_LONG).show()
    }


    private fun startObserving() {
        viewModel.stateLiveData.observe(this, Observer { state -> handleState(state) })
    }

    abstract fun handleState(state: S)

    fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}