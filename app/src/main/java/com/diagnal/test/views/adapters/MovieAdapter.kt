package com.diagnal.test.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diagnal.test.BR
import com.diagnal.test.R
import com.diagnal.test.databinding.ItemNetworkStateBinding
import com.diagnal.test.databinding.MovieListItemBinding
import com.diagnal.test.models.*
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter(private  var netWorkState: NetWorkState) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private val items: ArrayList<Content> = ArrayList<Content>()
    private var filterList:ArrayList<Content> = ArrayList<Content>()
    init {
        filterList = items
    }
    private fun updateItems(value: List<Content>) {
        this.items.addAll(value)
        filterList = items
    }

    private val TYPE_PROGRESS: Int = 0;
    private  val TYPE_ITEM: Int = 1;


    private fun hasExtraRow(): Boolean {
        return when (netWorkState) {
            is Loading -> true

            else -> false
        }
    }

    fun update(state: NetWorkState) {

        this.netWorkState = state


        when (state) {
            is Loading -> notifyItemInserted(itemCount)
            is Success -> {
                notifyItemRemoved(itemCount)
                updateItems(state.movies)
            }
            is Failed -> notifyItemRemoved(itemCount)
        }

    }

    fun getState() = netWorkState;

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is MovieHolder) {
            holder.item.moviePoster.setImageBitmap(null)
            holder.item.movieName.text = null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_PROGRESS) {
            val networkState: ItemNetworkStateBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_network_state,
                    parent,
                    false
                )
            NetworkStateViewHolder(networkState)
        } else {
            val movieBinding: MovieListItemBinding = DataBindingUtil.inflate<MovieListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.movie_list_item, parent, false
            )
            return MovieHolder(movieBinding)
        }


    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS;
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return when (netWorkState) {
            is Loading -> filterList.size + 1
            else -> filterList.size
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieHolder) {
            holder.bind(filterList[position])
        } else {
            val view = holder as NetworkStateViewHolder
            view.bind(netWorkState)

        }
    }


    class MovieHolder(val item: MovieListItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(data: Content) {
            item.setVariable(BR.item, data)

            item.executePendingBindings();


        }

    }

    class NetworkStateViewHolder(val itemview: ItemNetworkStateBinding) :
        RecyclerView.ViewHolder(itemview.root) {

        fun bind(networkState: NetWorkState) {
            itemview.progressBar.visibility = when (networkState) {

                is Success -> View.GONE

                else -> View.VISIBLE
            }


        }

    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    items
                } else {
                    val resultList = ArrayList<Content>()
                    for (row in items) {
                        if (row.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Content>
                notifyDataSetChanged()
            }

        }

    }

}

