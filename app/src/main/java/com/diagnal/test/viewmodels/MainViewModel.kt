package com.diagnal.test.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diagnal.test.models.Failed
import com.diagnal.test.models.NetWorkState
import com.diagnal.test.repository.DataRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    public val searchEnabled: ObservableBoolean = ObservableBoolean(false)
    public val searchText: ObservableField<String> = ObservableField()
    private val _movieLiveData: MutableLiveData<NetWorkState> = MutableLiveData()
    val movieLiveData: LiveData<NetWorkState>
        get() = _movieLiveData
    private var currentPage = 0;
    private val folder: String = "API"
    private val totalItems = 3;


    fun fetchData() {
        if (currentPage < totalItems) {
            dataRepository.fetchData(currentPage, folder, _movieLiveData)
            currentPage++
        } else {
            _movieLiveData.value = Failed("End Of List")
        }

    }

    fun onClearSearch() {
        searchText.set("")
        searchEnabled.set(!searchEnabled.get())
    }

    fun onSearchClick() {
        searchEnabled.set(!searchEnabled.get())
    }

}