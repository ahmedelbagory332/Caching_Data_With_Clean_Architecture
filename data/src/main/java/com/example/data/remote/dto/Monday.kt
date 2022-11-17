package com.example.domain.dto

import com.google.gson.annotations.SerializedName

data class Monday(
    val closes_at: String,
    @SerializedName("is_closed")
    val isClosed: Boolean,
    @SerializedName("opens_at")
    val opensAt: Boolean,
)