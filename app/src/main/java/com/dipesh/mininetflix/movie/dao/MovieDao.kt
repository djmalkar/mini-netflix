package com.dipesh.mininetflix.movie.dao

data class MovieDao (
    val id: Long,
    val posterPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val releaseDate: String,
    val video: Boolean,
    val avgVote: Double,
    val voteCount: Int
)