package com.example.iouproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.iouproject.composable.VideoCompose

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.LightGray
            ) {
                VideoCompose(videoUri = "https://dc5jkysis23ep.cloudfront.net/streams/e9a35b6f9d0bc631f4afd283ff606cbc-6/playlist.m3u8")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VideoCompose(videoUri = "https://dc5jkysis23ep.cloudfront.net/streams/e9a35b6f9d0bc631f4afd283ff606cbc-6/playlist.m3u8")
}