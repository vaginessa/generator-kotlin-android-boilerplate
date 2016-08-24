package <%= appPackage %>.ui.list

import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import <%= appPackage %>.ApplicationComponent
import <%= appPackage %>.R
import <%= appPackage %>.data.remote.model.Repo
import <%= appPackage %>.extensions.hide
import <%= appPackage %>.extensions.show
import <%= appPackage %>.extensions.showSnackbar
import <%= appPackage %>.ui.base.PresenterActivity
import <%= appPackage %>.ui.detail.DetailActivity
import <%= appPackage %>.ui.misc.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.empty_view.*
import timber.log.Timber
import javax.inject.Inject

class ListActivity : PresenterActivity<ListView, ListPresenter>(), ListView {

    companion object {
        val ADAPTER_DATA_KEY = "REPOS_LIST"
    }

    @Inject
    lateinit var adapter: ListAdapter

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var dividerDecorator: SimpleDividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(listToolbar)
        setupRecyclerView()
        setupSwipeRefresh()
        updateEmptyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(ADAPTER_DATA_KEY, adapter.getRepos())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val repos: List<Repo> = savedInstanceState.getParcelableArrayList(ADAPTER_DATA_KEY)
        updateList(repos)
    }

    private fun setupSwipeRefresh() {
        listSwipeRefresh.setOnRefreshListener {
            presenter.getKotlinRepos()
        }
    }

    private fun setupRecyclerView() {
        listRecyclerView.adapter = adapter
        listRecyclerView.layoutManager = layoutManager
        listRecyclerView.addItemDecoration(dividerDecorator)

        adapter.setClickListener {
            onItemClick(it)
        }
    }

    override fun injectDependencies(graph: ApplicationComponent) {
        graph.plus(ListModule(this))
                .injectTo(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun onItemClick(repo: Repo) {
        startActivity(DetailActivity.newIntent(this, repo))
    }

    override fun startLoading() {
        listSwipeRefresh.isRefreshing = true
    }

    override fun stopLoading() {
        listSwipeRefresh.isRefreshing = false
    }

    override fun updateList(repos: List<Repo>) {
        adapter.updateRepos(repos)
        stopLoading()
        updateEmptyView()
    }

    private fun updateEmptyView() {
        if (adapter.itemCount == 0) {
            empty_view.show()
        } else {
            empty_view.hide()
        }
    }

    override fun errorNoNetwork() {
        stopLoading()
        listCoordinatorLayout.showSnackbar(R.string.list_error_no_network)
    }

    override fun errorFetchRepos() {
        stopLoading()
        listCoordinatorLayout.showSnackbar(R.string.list_error_failed_fetch)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                Timber.d("Settings menu clicked!")
                true
            }

            R.id.action_night -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                recreate()
                true
            }

            R.id.action_day -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                recreate()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
