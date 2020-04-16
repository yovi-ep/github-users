package yovi.putra.github.features

import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import yovi.putra.github.R
import yovi.putra.github.core.base.BaseActivity
import yovi.putra.github.core.utils.network.NetworkThrowable.errorMessage
import yovi.putra.github.core.utils.state.LoaderState
import yovi.putra.github.core.utils.state.ResultState
import yovi.putra.github.core.utils.ui.invisible
import yovi.putra.github.core.utils.ui.visible
import yovi.putra.github.data.model.SearchResponse
import yovi.putra.hackernews.core.utils.ui.toast

class MainActivity : BaseActivity() {
    private lateinit var adapter: MainAdapter
    private val mainVM : MainViewModel by viewModel()

    override fun setupLayoutId(): Int = R.layout.activity_main

    override fun setupData(savedInstanceState: Bundle?) {
        adapter = MainAdapter()

        mainVM.loader.observe(this, loaderObserver)
        mainVM.searchResponse.observe(this, searchResultObserver)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        rv_item.layoutManager = LinearLayoutManager(this)
        rv_item.adapter = adapter

        et_serachview.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean = true
            override fun onQueryTextChange(p0: String?): Boolean {
                mainVM.searchUser(p0)
                return true
            }
        })
    }

    private var loaderObserver = Observer<LoaderState> {
        when (it) {
            is LoaderState.Show -> pb_loader.visible()
            is LoaderState.Hide -> pb_loader.invisible()
        }
    }

    private val searchResultObserver = Observer<ResultState> {
        when (it) {
            is ResultState.Success<*> -> {
                when (it.data) { is SearchResponse -> { adapter.setItem(it.data.items) } }
            }
            is ResultState.Error -> {
                toast(errorMessage(this, it.error))
            }
        }
    }
}
