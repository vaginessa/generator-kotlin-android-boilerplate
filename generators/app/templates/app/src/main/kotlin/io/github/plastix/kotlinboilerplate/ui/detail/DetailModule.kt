package <%= appPackage %>.ui.detail

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import <%= appPackage %>.data.remote.model.Repo
import <%= appPackage %>.ui.base.ActivityModule

@Module
class DetailModule(activity: AppCompatActivity, val repo: Repo) : ActivityModule(activity) {

    @Provides
    fun provideRepo(): Repo = repo
}