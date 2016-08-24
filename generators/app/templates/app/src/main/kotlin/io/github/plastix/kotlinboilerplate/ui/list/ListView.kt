package <%= appPackage %>.ui.list

import <%= appPackage %>.data.remote.model.Repo
import <%= appPackage %>.ui.base.MvpView

interface ListView : MvpView {

    fun updateList(repos: List<Repo>)

    fun startLoading()

    fun stopLoading()

    fun errorNoNetwork()

    fun errorFetchRepos()

}