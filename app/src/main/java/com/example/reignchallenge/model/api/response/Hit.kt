package com.example.reignchallenge.model.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hit{

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("story_title")
    @Expose
    var storyTitle: String? = null

    @SerializedName("story_url")
    @Expose
    var storyUrl: String? = null

    @SerializedName("objectID")
    @Expose
    var objectID: Long? = null

}
