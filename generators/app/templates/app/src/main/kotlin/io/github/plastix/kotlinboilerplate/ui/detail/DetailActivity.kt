package <%= appPackage %>.ui.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import <%= appPackage %>.ApplicationComponent
import <%= appPackage %>.R
import <%= appPackage %>.data.remote.model.Repo
import <%= appPackage %>.databinding.ActivityDetailBinding
import <%= appPackage %>.extensions.enableToolbarBackButton
import <%= appPackage %>.ui.base.ViewModelActivity

open class DetailActivity : ViewModelActivity<DetailViewModel, ActivityDetailBinding>() {

    companion object {
        val EXTRA_REPO_OBJECT = "REPO_ITEM"

        fun newIntent(context: Context, repo: Repo): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_REPO_OBJECT, repo)
            return intent
        }
    }

    private lateinit var repo: Repo

    override fun onCreate(savedInstanceState: Bundle?) {
        // We need to inject our Book into the Dagger graph
        // Thus we need grab it from the intent before we inject dependencies in super.onCreate()
        repo = intent.getParcelableExtra(EXTRA_REPO_OBJECT)

        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.detailToolbar)
        enableToolbarBackButton()
    }

    override fun onBind() {
        super.onBind()
        binding.viewModel = viewModel
    }

    override fun getViewBinding(): ActivityDetailBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_detail)
    }

    override fun injectDependencies(graph: ApplicationComponent) {
        graph.plus(DetailModule(this, repo))
                .injectTo(this)
    }
}
