package com.takasima.posapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takasima.posapp.R
import com.takasima.posapp.ui.theme.Neutral
import com.takasima.posapp.ui.theme.Primary
import com.takasima.posapp.ui.theme.Secondary

@Composable
fun NormalTextComponent(value: String) {
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black
    )
}
@Composable
fun HeadingTextComponent3(value: String) {
    Text(text = value,
        modifier = Modifier
//            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Secondary
    )
}

@Composable
fun WidthButton(value: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(colors = ButtonDefaults.buttonColors(containerColor = Primary),
        onClick = { onClick() },
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = value)
    }
}
@Composable
fun WidthButtonInverse(value: String, onClick: () -> Unit) {
    Button(colors = ButtonDefaults.buttonColors(containerColor = Neutral),
        border = BorderStroke(1.dp, Color.Gray),
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = value, color = Color.Black)
    }
}

@Composable
fun TextFieldCommon(labelValue: String, hintTextValue: String, changedValue: MutableState<String>){
    Column(modifier = Modifier.padding(vertical = 6.dp)) {

        HeadingTextComponent3(value = labelValue)
        Spacer(modifier = Modifier.height(4.dp))
        MyTextFieldComponent(labelValue = hintTextValue, Icons.Default.Person, changedValue)
    }
}
//@OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent(labelValue: String, icon: ImageVector, textValue: MutableState<String>) {

//    val textValue = remember { mutableStateOf("") }
    
    TextField(value = textValue.value,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .height(48.dp),
        label = { Text(text = labelValue)},
        keyboardOptions = KeyboardOptions.Default,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = "", modifier = Modifier.padding(start = 4.dp))}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(labelValue: String, icon: ImageVector, password: MutableState<String>) {

//    val password = remember { mutableStateOf("") }

//    val passwordVisible = remember { mutableStateOf(false) }
    TextField(value = password.value,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50)),
        label = { Text(text = labelValue)},
        keyboardOptions = KeyboardOptions.Default,
        onValueChange = {
            password.value = it
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = "", modifier = Modifier.padding(start = 4.dp))},
        trailingIcon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = "")}
    )
}


@Preview(showBackground = true)
@Composable
fun ComponentsPrev() {
        NormalTextComponent(value = "Normal Text")
//        WidthButton(value = "LOGIN")
    }