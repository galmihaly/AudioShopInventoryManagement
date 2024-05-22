package com.example.audioshopinventorymanagement.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import javax.inject.Inject

class Network @Inject constructor(
    private val context : Context
) {
    private var connectivityManager : ConnectivityManager? = null
    init {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun isOnline() : Boolean{
        var isOnline = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager!!.getNetworkCapabilities(connectivityManager!!.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        isOnline = true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        isOnline = true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        isOnline = true
                    }
                }
            }
        }
        else {
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                isOnline = true
            }
        }

        return isOnline
    }
}