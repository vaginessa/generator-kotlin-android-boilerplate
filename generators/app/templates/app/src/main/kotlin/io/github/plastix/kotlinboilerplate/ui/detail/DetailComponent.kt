package <%= appPackage %>.ui.detail

import dagger.Subcomponent
import <%= appPackage %>.ui.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        DetailModule::class
))
interface DetailComponent {
    fun injectTo(activity: DetailActivity)
}