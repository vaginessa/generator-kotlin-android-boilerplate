package <%= appPackage %>.ui.list

import dagger.Subcomponent
import <%= appPackage %>.ui.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        ListModule::class
))
interface ListComponent {

    fun injectTo(activity: ListActivity)
}