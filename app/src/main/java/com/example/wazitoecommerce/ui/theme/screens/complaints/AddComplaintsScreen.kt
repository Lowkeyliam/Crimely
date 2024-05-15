package com.example.wazitoecommerce.ui.theme.screens.complaints

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.R
import com.example.wazitoecommerce.data.ComplaintViewModel
import com.example.wazitoecommerce.navigation.ADD_PRODUCTS_URL
import com.example.wazitoecommerce.navigation.HOME_URL
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddComplaintsScreen(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.img_2),
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(
            title = { Text(
                text = "Add Complaints",
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            ) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent),
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(HOME_URL)
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black)
                }
            }
        )
        var comlainantName by remember { mutableStateOf("") }
        var complaintdes by remember { mutableStateOf("") }
        var phoneno by remember { mutableStateOf("") }
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = comlainantName,
            onValueChange = { comlainantName = it },
            label = { Text(text = "Complainant") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = complaintdes,
            onValueChange = { complaintdes = it },
            label = { Text(text = "Complaint Description") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = phoneno,
            onValueChange = { phoneno = it },
            label = { Text(text = "Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(20.dp))



        //---------------------IMAGE PICKER START-----------------------------------//

        var modifier = Modifier
        ImagePicker(modifier,context, navController, comlainantName.trim(), complaintdes.trim(), phoneno.trim())

        //---------------------IMAGE PICKER END-----------------------------------//



    }
}

@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context,navController: NavHostController, complainant:String, complaintdes:String, phoneno:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 50.dp, end = 50.dp)
            ){
                Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image", modifier = Modifier.fillMaxSize())
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            OutlinedButton(
                onClick = {
                    imagePicker.launch("image/*")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color.White)
            ) {
                Text(
                    text = "Attach Photo",
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(onClick = {
                navController.navigate(HOME_URL)
                var complaintRepository = ComplaintViewModel(navController,context)
                complaintRepository.uploadComplaint(complainant, complaintdes, phoneno,imageUri!!)


            },modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color.White)

            ) {
                Text(
                    text = "REPORT",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddComplaintsScreenPreview(){
    WazitoECommerceTheme {
        AddComplaintsScreen(navController = rememberNavController())
    }
}