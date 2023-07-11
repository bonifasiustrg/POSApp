package com.takasima.posapp.ui.screen.common.product

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.takasima.posapp.ui.theme.Primary
import java.io.ByteArrayOutputStream

@Composable
fun ImageUploadAndConvert(context: Context) {
    // Create a state to hold the selected image
    val selectedImage = remember { mutableStateOf<Bitmap?>(null) }

    // Create a launcher for the image selection activity
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { selectedUri ->
            // Get the bitmap from the selected image URI
//            val imageLoader = ImageLoader.Builder(context)
//                .components(fun ComponentRegistry.Builder.() {
//                    add(SvgDecoder(context))
//                })
//                .build()
//
//            val disposable = imageLoader.load(selectedUri) {
//                target { drawable ->
//                    val bitmap = (drawable as? BitmapDrawable)?.bitmap
//                    selectedImage.value = bitmap
//                }
//            }
            // Dispose of the disposable when necessary (e.g., when the composable is disposed)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(text = "Select Image")
        }

        // Display the selected image, if available
        selectedImage.value?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected Image",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(
                onClick = {
                    // Convert the bitmap to base64 text
                    val base64Text = convertBitmapToBase64(bitmap)
                    // TODO: Perform POST request with the base64Text
                }
            ) {
                Text(text = "Upload Image")
            }
        }
    }
}

// Convert a Bitmap to base64 text
//private fun convertBitmapToBase64(bitmap: Bitmap): String {
//    val byteArrayOutputStream = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
//    val byteArray = byteArrayOutputStream.toByteArray()
//    return Base64.encodeToString(byteArray, Base64.DEFAULT)
//}
private fun convertBitmapToBase64(bitmap: Bitmap?): String {
    if (bitmap == null) return "bitmap null"

    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

@Preview
@Composable
fun PreviewImageUploadAndConvert() {
    ImageUploadAndConvert(context = LocalContext.current)
}
@Composable
fun RequestContentPermission(base64Text: MutableState<String>) {
//    val base64Text = remember { mutableStateOf("") }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    val bitmap =  remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }
    Column(Modifier.fillMaxWidth()) {
        OutlinedButton(onClick = {
            launcher.launch("image/*")
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Primary
            ),
            border = BorderStroke(1.dp, Primary),
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Pick image")
        }

        Spacer(modifier = Modifier.height(12.dp))

        imageUri?.let { uri ->
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                val inputStream = context.contentResolver.openInputStream(uri)
                inputStream?.use { stream ->
                    val drawable = Drawable.createFromStream(stream, uri.toString())
                    drawable?.toBitmap()
                }
            }

            bitmap?.let { bmp ->
                // Use the bitmap as needed
                base64Text.value = convertBitmapToBase64(bmp)
                Log.e("base64Text", base64Text.toString())
            }
        }
    }
}