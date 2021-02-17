package com.tserr.tmdbview.view.lazymovielist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.tserr.tmdbview.domain.Movie
import com.tserr.tmdbview.view.R
import com.tserr.tmdbview.view.ui.utils.PicassoImage
import timber.log.Timber

@Composable
fun LazyMovieList(
    movies: LazyPagingItems<Movie>,
    modifier: Modifier,
    posterModifier: Modifier,
    onMovieClicked: (String) -> Unit
) {
    Timber.d("LazyMovieList movies: ${movies.itemCount}")
    LazyColumn {
        items(movies) { movie ->
            if (movie != null) {
                MovieItem(movie, modifier, posterModifier, onMovieClicked)
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier,
    posterModifier: Modifier,
    onMovieClicked: (String) -> Unit
) {
    Row(
        modifier = modifier.clickable { onMovieClicked(movie.id) }
    ) {
        movie.poster?.let { url ->
            PicassoImage(
                url = url,
                placeholderImage = imageResource(R.drawable.poster_placeholder),
                modifier = posterModifier
            )
        }
        Column {
            Text(text = movie.title, style = MaterialTheme.typography.h6)
            movie.releaseDate?.let {
                Text(text = it, style = MaterialTheme.typography.body1)
            }
        }
    }
    Divider(color = Color.Black)
}
