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

@JsonClass(generateAdapter = true)
data class MovieCreditSchema(
  val id: Int,
  val cast: List<CastSchema>,
  val crew: List<CrewSchema>
)

@JsonClass(generateAdapter = true)
data class CastSchema(
  val adult: Boolean,
  @Json(name = "cast_id")
  val castId: Int,
  val character: String,
  @Json(name = "credit_id")
  val creditId: String,
  val gender: Int,
  val id: Int,
  @Json(name = "known_for_department")
  val knownForDepartment: String,
  val name: String,
  val order: Int,
  @Json(name = "original_name")
  val originalName: String,
  val popularity: Double,
  @Json(name = "profile_path")
  val profilePath: String?
)

@JsonClass(generateAdapter = true)
data class CrewSchema(
  val adult: Boolean,
  @Json(name = "credit_id")
  val creditId: String,
  val department: String,
  val gender: Int,
  val id: Int,
  val job: String,
  @Json(name = "known_for_department")
  val knownForDepartment: String,
  val name: String,
  @Json(name = "original_name")
  val originalName: String,
  val popularity: Double,
  @Json(name = "profile_path")
  val profilePath: String?
)