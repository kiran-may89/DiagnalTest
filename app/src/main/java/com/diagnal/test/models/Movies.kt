package com.diagnal.test.models

import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Page
)
data class Content(
    val name: String,
    @SerializedName("poster-image")
    val poster_image: String
){

}

data class ContentItems(
    val content: List<Content>
)

data class Page(
    @SerializedName("content-items")
    val content_items: ContentItems,
    @SerializedName("page-num")
    val page_num: Int,
    @SerializedName("page-size")
    val page_size: Int,
    val title: String,
    @SerializedName("total-content-items")
    val total_content_items: String
)