package com.example.duckytv.screens.player

import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.tv.material3.Text
import com.example.duckytv.utils.Constants

@Composable
fun KeepScreenOn() {
    val currentView = LocalView.current
    DisposableEffect(Unit) {
        currentView.keepScreenOn = true
        onDispose {
            currentView.keepScreenOn = false
        }
    }
}


@Composable
fun PlayerScreen(navController: NavController, channelId: String?){
    val mediaUrl = "${Constants.BASE_URL}/play/$channelId/index.m3u8"

    KeepScreenOn()
    PlayerScreenContent(mediaUrl = mediaUrl,
        onBackPressed = {
            navController.popBackStack()
        }
    )
}


@OptIn(UnstableApi::class)
@Composable
fun PlayerScreenContent(modifier: Modifier = Modifier, mediaUrl: String, onBackPressed: () -> Unit) {
        var lifecycle by remember {
            mutableStateOf(Lifecycle.Event.ON_CREATE)
        }
        val context = LocalContext.current

        val mediaItem =
            MediaItem.fromUri(mediaUrl)

//      progressive video:
      /*  val mediaSource: MediaSource =
            ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                .createMediaSource(mediaItem)*/

//        non progressive video:
        val mediaSource: MediaSource =
            HlsMediaSource.Factory(DefaultHttpDataSource.Factory())
                .createMediaSource(mediaItem)


        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                playWhenReady = true
                repeatMode = ExoPlayer.REPEAT_MODE_ALL
                setMediaSource(mediaSource)
                prepare()

            }
        }


        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(key1 = lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                lifecycle = event
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                exoPlayer.release()
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }


        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            factory = {

                PlayerView(context).also { playerView ->
                    playerView.player = exoPlayer
                }
            },
            update = {
                when (lifecycle) {

                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()

                        it.player?.play()
                    }

                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()

                        it.player?.pause()

                    }

                    else -> null
                }
            }
        )

}