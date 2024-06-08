package com.dipesh.mininetflix.networking.question

import com.dipesh.mininetflix.networking.user.UserSchema
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionSchema(
    @Json(name = "title") val title: String,
    @Json(name = "question_id") val id: String,
    @Json(name = "owner") val owner: UserSchema,
)