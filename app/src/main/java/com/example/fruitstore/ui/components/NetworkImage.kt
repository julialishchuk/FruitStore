package com.example.fruitstore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.fruitstore.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String?
) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    if (url.isNullOrEmpty()) {
                        placeholder(R.drawable.loader)
                    } else {
                        error(R.drawable.loader)
                    }
                }).build()
        )
    Image(
        painter = painter,
        contentDescription = null,
        modifier,
        contentScale = ContentScale.Fit
    )
}

@Preview
@Composable
fun PreviewNetworkImage() {
    NetworkImage(url = "https://test-task-server.mediolanum.f17y.com/images/cherry.png")
}
