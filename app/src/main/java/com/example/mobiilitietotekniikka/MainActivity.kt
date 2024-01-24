package com.example.mobiilitietotekniikka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobiilitietotekniikka.ui.theme.MobiilitietotekniikkaTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.runtime.mutableIntStateOf


private var isImageClicked = mutableStateOf(false)
private var c_window = mutableStateOf(0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobiilitietotekniikkaTheme {
                when (c_window.value){
                    0 -> {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.height(400.dp).verticalScroll(rememberScrollState())) {
                        Spacer(modifier = Modifier.height(100.dp))
                        if (isImageClicked.value) {
                            c_window.value = 2;
                            isImageClicked.value = false

                        }
                        Image(painter = painterResource(id = R.drawable.janna), contentDescription =
                        "My Image", modifier = Modifier.clickable{
                            isImageClicked.value = !isImageClicked.value
                        }
                        )
                        Spacer(modifier = Modifier.height(1000.dp))
                        Text("Toimii")
                    }
                }
            }



            2 -> {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        Column(modifier = Modifier.height(400.dp).verticalScroll(rememberScrollState())) {
                            Text("Juuh")
                            Spacer(modifier = Modifier.height(100.dp))
                            if (isImageClicked.value) {
                                c_window.value = 0;
                                isImageClicked.value = false
                            }
                            Image(painter = painterResource(id = R.drawable.janna), contentDescription =
                            "My Image", modifier = Modifier.clickable{
                                isImageClicked.value = !isImageClicked.value
                            }
                            )
                        }
                    }
                }
            }
        }
    }
}
}
