package com.dipesh.mininetflix.networking.genres

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresSchema(
  val genres: List<GenreSchema>
)

@JsonClass(generateAdapter = true)
data class GenreSchema(
  val id: Long,
  val name: String
)