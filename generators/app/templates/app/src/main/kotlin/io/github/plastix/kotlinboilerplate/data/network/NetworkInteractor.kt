package <%= appPackage %>.data.network

import rx.Completable

interface NetworkInteractor {

    fun hasNetworkConnection(): Boolean

    fun hasNetworkConnectionCompletable(): Completable

    class NetworkUnavailableException : Throwable("No network available!")
}