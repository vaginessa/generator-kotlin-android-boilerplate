package <%= appPackage %>.ui.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import <%= appPackage %>.ApplicationComponent
import <%= appPackage %>.KotlinBoilerplateApp

abstract class BaseFragment : Fragment() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(KotlinBoilerplateApp.graph)
    }

    abstract fun injectDependencies(graph: ApplicationComponent)

}