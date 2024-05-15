package com.example.wazitoecommerce.ui.theme.screens.complaints

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.wazitoecommerce.data.ComplaintViewModel
import com.example.wazitoecommerce.models.Complaint
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme

@Composable
fun ViewComplaintsScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var complaintRepository = ComplaintViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Complaint("","","","","")) }
        var emptyComplaintsListState = remember { mutableStateListOf<Complaint>() }

        var complaints = complaintRepository.allComplaints(emptyProductState, emptyComplaintsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All products",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(complaints){
                    ComplaintItem(
                        complainant = it.complainant,
                        complaintdes = it.complaintdes,
                        phoneno = it.phoneno,
                        id = it.id,
                        navController = navController,
                        complaintRepository = complaintRepository,
                        complainantImage = it.imageUrl
                    )
                }
            }
        }
    }
}


@Composable
fun ComplaintItem(complainant:String, complaintdes:String, phoneno:String, id:String,
                navController:NavHostController,
                complaintRepository:ComplaintViewModel, complainantImage:String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = complainant)
        Text(text = complaintdes)
        Text(text = phoneno)
        Image(
            painter = rememberAsyncImagePainter(complainantImage),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
        Button(onClick = {
            complaintRepository.deleteComplaint(id)
        }) {
            Text(text = "Solved")
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ViewComplaintsScreenPreview(){
    WazitoECommerceTheme {
        ViewComplaintsScreen(navController = rememberNavController())
    }
}