package com.example.moviesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_table")
data class MovieItem(
    @PrimaryKey
    val id: String,
    val crew: String,
    val fullTitle: String,

    @ColumnInfo
    val imDbRating: String,
    val imDbRatingCount: String,

    @ColumnInfo
    val image: String,
    val rank: String,
    val rankUpDown: String,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val year: String
) : Serializable