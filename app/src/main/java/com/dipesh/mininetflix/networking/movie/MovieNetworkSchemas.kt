package com.dipesh.mininetflix.networking.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesSchema(
  val page: Int,
  val results: List<MovieSchema>
)

@JsonClass(generateAdapter = true)
data class MovieSchema(
  val id: Long,
  val adult: Boolean,
  @Json(name = "poster_path") val posterPath: String,
  @Json(name = "genre_ids") val genreIds: List<Int>,
  @Json(name = "original_language") val originalLanguage: String,
  val title: String,
  val overview: String,
  val popularity: Double,
  @Json(name = "release_date") val releaseDate: String,
  val video: Boolean,
  @Json(name = "vote_average") val avgVote: Double,
  @Json(name = "vote_count") val voteCount: Int
)