package com.takasima.posapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.ModifierLocalMap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takasima.posapp.R
import com.takasima.posapp.ui.theme.Primary

@Composable
fun ProductImageCard(/*
    painter: Painter,
    contentDscription: String,
    title: String,
    modifier: Modifier = Modifier*/
    ) {
    Card(
        modifier = Modifier.height(200.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.size(200.dp)) {
            Image(
                painter = painterResource(id = R.drawable.food),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
                    Text(text = "Panna Cotta", style = TextStyle(color = Color.White, fontSize = 16.sp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Rp50.000", style = TextStyle(color = Color.White, fontSize = 16.sp))
                }
            }
        }
    }
}


@Composable
fun OrderImageCard(/*
    painter: Painter,
    contentDscription: String,
    title: String,
    modifier: Modifier = Modifier*/
) {
    Card(
        modifier = Modifier.height(200.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.size(200.dp)) {
            Image(
                painter = painterResource(id = R.drawable.food),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column() {
                        Text(text = "Panna Cotta", style = TextStyle(color = Color.White, fontSize = 16.sp))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Rp50.000", style = TextStyle(color = Color.White, fontSize = 16.sp))
                    }
                    Button(onClick = { /*TODO*/ },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(Primary)
                    ) {
//                        Text(text = "HALO")
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductPrev() {
    ProductImageCard()
}
@Preview
@Composable
fun ProductOrderPrev() {
    OrderImageCard()
}
