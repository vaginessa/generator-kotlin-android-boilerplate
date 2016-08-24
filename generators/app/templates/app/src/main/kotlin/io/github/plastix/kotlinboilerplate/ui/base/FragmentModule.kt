package <%= appPackage %>.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import <%= appPackage %>.ui.FragmentScope

@Module
abstract class FragmentModule(private val fragment: Fragment) {

    @Provides @FragmentScope
    fun provideFragment(): Fragment = fragment

    @Provides @FragmentScope
    fun provideFragmentContext(): Context = fragment.context
}