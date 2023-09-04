package com.example.laboratorio6_pm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio6_pm.ui.theme.Laboratorio6_PMTheme
import androidx.compose.material3.Surface as Surface1


class Galeria : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio6_PMTheme {
                // A surface container using the 'background' color from the theme
                Surface1(
                    modifier = Modifier.fillMaxSize()
                    //color = MaterialTheme.colorScheme.background
                ) {
                    CardMenuGaleria()
                }
            }
        }
    }
}

data class GalleryItem(val imageResource: Int, val textoResource: String)

@Composable
fun CardMenuGaleria() {
    val context = LocalContext.current
    var result by remember { mutableStateOf(1) }

    val galleryItems = listOf(
        GalleryItem(R.drawable.pisaje1suiza, "Paisaje 1"),
        GalleryItem(R.drawable.pisaje2, "Paisaje 2"),
        GalleryItem(R.drawable.pisaje3, "Paisaje 3"),
        GalleryItem(R.drawable.pisaje4, "Paisaje 4"),
        GalleryItem(R.drawable.pisaje5, "Paisaje 5"),
        GalleryItem(R.drawable.pisaje6, "Paisaje 6"),
        GalleryItem(R.drawable.pisaje7, "Paisaje 7"),
        GalleryItem(R.drawable.pisaje8, "Paisaje 8"),
        GalleryItem(R.drawable.pisaje9, "Paisaje 9"),
        GalleryItem(R.drawable.pisaje10, "Paisaje 10")
    )

    val currentItem = galleryItems.getOrNull(result - 1) ?: galleryItems[0]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {        }
        Surface1(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp),
                ),
            shape = RoundedCornerShape(20.dp),
            color = Color.DarkGray,
            content = {
                Image(
                    painter = painterResource(id = currentItem.imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(40.dp)
                        .height(400.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        )
        Surface1(

            color = Color.Black
        ) {
            Column {
                Text(
                    text = currentItem.textoResource,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    fontStyle = FontStyle.Italic,
                )
                Text(
                    text = "Autor: Youtube",
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .align(alignment = Alignment.End),
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right,
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Button(
                onClick = {
                    if (result > 1) {
                        result -= 1
                    } else {
                        result = galleryItems.size
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )
            ) {
                Text(
                    text = "Anterior",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(5.dp),
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = {
                    if (result < galleryItems.size) {
                        result += 1
                    } else {
                        result = 1
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )

            ) {
                Text(
                    text = "Siguiente",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,

                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Galeria"
)
@Composable
fun GreetingPreviewGaleria() {
    Laboratorio6_PMTheme {
        CardMenuGaleria()
    }
}