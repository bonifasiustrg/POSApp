package com.takasima.posapp.ui.screen.common.product

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.ByteArrayOutputStream

//@Composable
//fun ImageUploadAndConvert(context: Context) {
//    // Create a state to hold the selected image
//    val selectedImage = remember { mutableStateOf<Bitmap?>(null) }
//
//    // Create a launcher for the image selection activity
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        uri?.let { selectedUri ->
//            // Get the bitmap from the selected image URI
//            val imageBitmap = ImageBitmap
//                .createFromUri(context, selectedUri)
//                .asAndroidBitmap()
//            selectedImage.value = imageBitmap
//        }
//    }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Button(
//            onClick = { launcher.launch("image/*") },
//            modifier = Modifier.padding(bottom = 8.dp)
//        ) {
//            Text(text = "Select Image")
//        }
//
//        // Display the selected image, if available
//        selectedImage.value?.let { bitmap ->
//            Image(
//                bitmap = bitmap.asImageBitmap(),
//                contentDescription = "Selected Image",
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//
//            Button(
//                onClick = {
//                    // Convert the bitmap to base64 text
//                    val base64Text = convertBitmapToBase64(bitmap)
//                    // TODO: Perform POST request with the base64Text
//                }
//            ) {
//                Text(text = "Upload Image")
//            }
//        }
//    }
//}

// Convert a Bitmap to base64 text
private fun convertBitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}
