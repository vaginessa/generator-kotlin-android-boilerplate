package <%= appPackage %>.ui.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import <%= appPackage %>.ui.ActivityScope

@Module
abstract class ActivityModule(private val activity: AppCompatActivity) {

    @Provides @ActivityScope
    fun provideActivity(): AppCompatActivity = activity

    @Provides @ActivityScope
    fun provideActivityContext(): Context = activity.baseContext
}