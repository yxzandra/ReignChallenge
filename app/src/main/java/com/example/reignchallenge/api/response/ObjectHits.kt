package com.example.reignchallenge.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ObjectHits {

    @SerializedName("hits")
    @Expose
    var hits: List<Hit>? = null
    @SerializedName("nbHits")
    @Expose
    var nbHits: Int? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("nbPages")
    @Expose
    var nbPages: Int? = null
    @SerializedName("hitsPerPage")
    @Expose
    var hitsPerPage: Int? = null
    @SerializedName("processingTimeMS")
    @Expose
    var processingTimeMS: Int? = null
    @SerializedName("exhaustiveNbHits")
    @Expose
    var exhaustiveNbHits: Boolean? = null
    @SerializedName("query")
    @Expose
    var query: String? = null
    @SerializedName("params")
    @Expose
    var params: String? = null

    companion object {
        private val serialVersionUID = 7779613074346367878L
    }

}
