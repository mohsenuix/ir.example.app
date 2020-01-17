package ir.example.app.domain.repository

interface ConnectivityManager {
    fun hasNetwork(): Boolean?
}