package com.tserr.tmdbview.view.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.tserr.tmdbview.domain.Movie
import com.tserr.tmdbview.view.R
import com.tserr.tmdbview.view.screen.screenWithTopBar
import com.tserr.tmdbview.view.topbar.tmdbViewTopBar
import com.tserr.tmdbview.view.ui.utils.PicassoImage


val movieDetails =
    screenWithTopBar<MovieDetailsViewModel>(
        topBar = tmdbViewTopBar("Movie Details")
    ) { _, viewModel, arguments, _ ->
        val movie: Movie? by remember { viewModel.movieState }

        viewModel.getMovieDetails(arguments?.getString("movieId"))

        movie?.run {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                poster?.let { url ->
                    PicassoImage(
                        url = url,
                        placeholderImage = imageResource(R.drawable.poster_placeholder),
                        modifier = Modifier.width(256.dp),
                    )
                }
                Text(text = title, style = MaterialTheme.typography.h6)
            }

            Column(Modifier.padding(12.dp)) {
                releaseDate?.let { Text("Release Date: $it") }
                budget?.let { Text("Budget: $it$") }
                popularity?.let { Text("Popularity $it") }
                Spacer(modifier = Modifier.height(24.dp))
                overview?.let { overview ->
                    Text(text = "Overview", style = MaterialTheme.typography.subtitle2)
                    Text(text = overview, style = MaterialTheme.typography.body2)
                }
            }
        }
    }