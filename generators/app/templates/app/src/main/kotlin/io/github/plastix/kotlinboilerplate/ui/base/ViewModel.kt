package <%= appPackage %>.ui.base

interface ViewModel {

    fun bind()

    fun unbind()

    fun onDestroy()
}