package com.tserr.tmdbview.view.ui.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

@Composable
fun PicassoImage(url: String?, placeholderImage: ImageBitmap, modifier: Modifier) {
    var imageBitmap: ImageBitmap by remember { mutableStateOf(placeholderImage) }
    Image(
        bitmap = imageBitmap,
        contentDescription = "Movie poster",
        modifier = modifier
    )

    if (url != null)
        DisposableEffect(url) {
            val target = object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    bitmap?.let { imageBitmap = bitmap.asImageBitmap() }
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }
            }
            val picasso = Picasso.get()
            picasso.load(url).into(target)

            onDispose {
                picasso.cancelRequest(target)
            }
        }
}
