package com.diagnal.test.views

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diagnal.test.R
import com.diagnal.test.databinding.ActivityMainBinding
import com.diagnal.test.di.modules.factory.ViewModelProviderFactory
import com.diagnal.test.models.Loading
import com.diagnal.test.models.NetWorkState
import com.diagnal.test.utils.ItemDecoration
import com.diagnal.test.viewmodels.MainViewModel
import com.diagnal.test.views.adapters.MovieAdapter
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>;

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
    }
    private val horizontalCount: Int by lazy {
        if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) 7 else 3
    }
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.executePendingBindings()

        initRecyclerView()
        initData()

        initTextListener();

    }

    private fun initTextListener() {
        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                (binding.movieList.adapter as MovieAdapter).filter.filter(s)
            }

        })

    }




    private fun initRecyclerView() {

        binding.movieList.layoutManager =
            GridLayoutManager(this, horizontalCount, RecyclerView.VERTICAL, false)

        binding.movieList.apply {
            adapter = MovieAdapter(netWorkState = Loading())

            addItemDecoration(ItemDecoration(resources.getDimensionPixelSize(R.dimen.size_8)))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (viewModel.searchEnabled.get()){
                        return
                    }
                    val adapter = (adapter as MovieAdapter)
                    if (dy > 0) {
                        val layoutManager = layoutManager as GridLayoutManager

                        val visibleItemCount =
                            layoutManager.findLastCompletelyVisibleItemPosition() + 1
                        if (visibleItemCount == layoutManager.itemCount
                            && (adapter.getState() !is Loading)
                        ) {
                            binding.root.post { adapter.update(Loading()) }
                            binding.root.postDelayed({
                                viewModel.fetchData()

                            }, 2000L)
                        }
                    }
                }
            })

        }
    }

    private fun initData() {
        val observer: Observer<NetWorkState> = Observer {
            (binding.movieList.adapter as MovieAdapter).update(it)


        }
        viewModel.movieLiveData.observe(this, observer)
        viewModel.fetchData()
    }


}

