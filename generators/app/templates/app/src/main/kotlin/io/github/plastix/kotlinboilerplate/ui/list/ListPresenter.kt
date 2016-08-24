package <%= appPackage %>.ui.list

import <%= appPackage %>.ui.base.Presenter

interface ListPresenter : Presenter<ListView>{

    fun getKotlinRepos()

}