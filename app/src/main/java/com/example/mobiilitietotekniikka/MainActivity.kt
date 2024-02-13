package com.example.mobiilitietotekniikka
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.example.mobiilitietotekniikka.ui.theme.MobiilitietotekniikkaTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage


private var c_window = mutableIntStateOf(0)
private var messages = mutableListOf<String>("moi","hello","hej")
private var profilePicture = R.drawable.janna
private var uri1: Uri? = null
private var message: String = ""

class MainActivity : ComponentActivity() {
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri1 = uri
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobiilitietotekniikkaTheme {
                when (c_window.intValue) {
                    0 -> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.LightGray
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Spacer(modifier = Modifier.height(100.dp))
                                for (message in messages) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp)
                                    ) {

                                        AsyncImage(
                                            model = uri1,
                                            contentDescription = null,
                                            error = painterResource(id = profilePicture),
                                            modifier = Modifier
                                                .size(60.dp)
                                                .clip(CircleShape)
                                                .clickable {
                                                    c_window.intValue = 1;
                                                })
                                        Text(
                                            text = message,
                                            style = MaterialTheme.typography.displaySmall,
                                            modifier = Modifier.clickable {
                                                c_window.intValue = 2;
                                            })
                                    }
                                }
                                Spacer(modifier = Modifier.height(1000.dp))
                            }
                        }
                    }

                    2 -> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.LightGray
                        ) {
                            Column(
                                modifier = Modifier
                                    .height(400.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {

                                var messageInput by remember { mutableStateOf(TextFieldValue()) }
                                BasicTextField(
                                    value = messageInput,
                                    onValueChange = { newValue: TextFieldValue ->
                                        messageInput = newValue
                                        message = newValue.text
                                    },

                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(75.dp)
                                        .padding(8.dp)
                                        .background(androidx.compose.ui.graphics.Color.White)
                                )
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.weight(1f))
                                Image(painter = painterResource(id = R.drawable.back_button),
                                    contentDescription =
                                    "My Image",
                                    modifier = Modifier
                                        .clickable {
                                            c_window.intValue = 0;
                                        }
                                        .size(50.dp))
                            }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(modifier = Modifier.weight(1f))
                            Image(painter = painterResource(id = R.drawable.send),
                                contentDescription =
                                "My Image",
                                modifier = Modifier
                                    .padding(vertical = 70.dp)
                                    .clickable {
                                        messages.add(message)

                                    }
                                    .size(50.dp))
                            }
                        }
                    }
                    1 -> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = Color.LightGray
                        ) {
                            Column(
                                modifier = Modifier
                                    .height(400.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Spacer(modifier = Modifier.height(100.dp))

                            }

                        }
                        Row(modifier = Modifier.fillMaxWidth()) {

                            AsyncImage(
                                model = uri1,
                                contentDescription = null,
                                error = painterResource(id = profilePicture),
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        getContent.launch("image/*")
                                    })
                                 }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(modifier = Modifier.weight(1f))
                            Image(painter = painterResource(id = R.drawable.back_button),
                                contentDescription =
                                "My Image",
                                modifier = Modifier
                                    .clickable {
                                        c_window.intValue = 0;

                                    }
                                    .size(50.dp))
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (c_window.intValue != 0) {
            c_window.intValue = 0
        } else {
            super.onBackPressed()
        }
    }
}