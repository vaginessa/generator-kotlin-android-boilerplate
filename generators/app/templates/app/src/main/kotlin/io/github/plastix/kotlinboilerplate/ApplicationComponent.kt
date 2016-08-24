package <%= appPackage %>

import dagger.Component
import <%= appPackage %>.data.network.NetworkModule
import <%= appPackage %>.data.remote.ApiModule
import <%= appPackage %>.ui.detail.DetailComponent
import <%= appPackage %>.ui.detail.DetailModule
import <%= appPackage %>.ui.list.ListComponent
import <%= appPackage %>.ui.list.ListModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkModule::class,
        ApiModule::class
))
interface ApplicationComponent {

    // Injectors
    fun injectTo(app: KotlinBoilerplateApp)

    // Submodule methods
    // Every screen is its own submodule of the graph and must be added here.
    fun plus(module: ListModule): ListComponent
    fun plus(module: DetailModule): DetailComponent
}