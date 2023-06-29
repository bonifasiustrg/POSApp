package com.takasima.posapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takasima.posapp.R
import com.takasima.posapp.ui.theme.Neutral
import com.takasima.posapp.ui.theme.Typography

@Composable
fun CardInfo(title: String, value: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10)),
        colors = CardDefaults.cardColors(containerColor = Neutral)
    ) {
        Column(Modifier.padding(16.dp)) {

            Text(text = title, style = Typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(text = value, style = Typography.headlineMedium, fontWeight = FontWeight.Bold)

            Image(painter = painterResource(id = R.drawable.chart2),
                contentDescription = "chart",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f)
            )
        }
    }

}

@Preview
@Composable
fun CardInfoPrev() {
    CardInfo("Pemasukan", "Rp. 10.212.765")
}