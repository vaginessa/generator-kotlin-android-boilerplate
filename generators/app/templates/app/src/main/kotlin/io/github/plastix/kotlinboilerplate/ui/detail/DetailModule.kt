package <%= appPackage %>.ui.detail

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import <%= appPackage %>.ui.ActivityScope
import <%= appPackage %>.ui.base.ActivityModule

@Module
class DetailModule(activity: AppCompatActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun providePresenter(presenter: DetailPresenterImpl): DetailPresenter = presenter
}