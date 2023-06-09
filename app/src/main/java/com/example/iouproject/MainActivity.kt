package com.example.iouproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iouproject.composable.VideoCompose
import com.example.iouproject.downloader.FileDownloader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val downloader = FileDownloader(this)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()) {

                    Row(modifier = Modifier
                        .align(Alignment.TopCenter)) {
                        Text(
                            text = stringResource(id = R.string.title),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(5.dp, 30.dp, 5.dp, 10.dp)
                        )
                    }
                    Row(modifier = Modifier
                        .align(Alignment.Center)) {

                        VideoCompose(videoUri = stringResource(id = R.string.video_url))
                    }
                    Row(modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(30.dp)) {
                        Button(
                            onClick = {
                                GlobalScope.launch {
                                    downloader.downloadFile(getString(R.string.video_url))
                                }
                            },
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(text = stringResource(id = R.string.download_video))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VideoCompose(videoUri = stringResource(id = R.string.video_url))
}