package com.takasima.posapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.takasima.posapp.R
import com.takasima.posapp.ui.theme.Neutral


@Composable
fun TransactionItem() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Neutral)

    ) {

        Icon(painter = painterResource(id = R.drawable.transaction_item), contentDescription = "transaction_item",
            modifier = Modifier
                .padding(8.dp)
                .padding(end = 8.dp)
                .size(24.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "BDI Restaurant", fontWeight = FontWeight.Bold)
            Text(text = "Rp. 1.000.000")
        }
        Text(text = "13.46", textAlign = TextAlign.End, modifier = Modifier.weight(1f).padding(8.dp))
    }
}