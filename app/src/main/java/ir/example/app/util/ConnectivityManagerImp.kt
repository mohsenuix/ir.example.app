package ir.example.app.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import ir.example.app.di.scope.ForApplication
import javax.inject.Inject

class ConnectivityManagerImp @Inject constructor(@ForApplication val context: Context) :
    ir.example.app.domain.repository.ConnectivityManager{
    override fun hasNetwork(): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}