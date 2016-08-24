package <%= appPackage %>.ui.list

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import <%= appPackage %>.ui.ActivityScope
import <%= appPackage %>.ui.base.ActivityModule

@Module
class ListModule(activity: AppCompatActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun providePresenter(presenter: ListPresenterImpl): ListPresenter = presenter

    @Provides @ActivityScope
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager = LinearLayoutManager(context)
}
