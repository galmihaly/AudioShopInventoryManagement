package com.example.audioshopinventorymanagement.startscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

object StartScreenComponents {

    @Composable
    fun buttonWithLogoAndText(buttonText: String, buttonLogoId: Int, buttonLogoHeight: Dp, buttonLogoWidth: Dp, onClick: () -> Unit){
        Button(
            onClick = onClick,
            modifier = Modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GREEN)
        ) {
            Column (
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = buttonLogoId),
                    contentDescription = "",
                    modifier = Modifier
                        .height(buttonLogoHeight)
                        .width(buttonLogoWidth)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = buttonText,
                    color = Color.White,
                    fontFamily = CustomFonts.RobotoMono_Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewComponent(){
    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithTextAndLogo(
                headLineText = "Inventory Management",
                headLineLogo = R.drawable.audioshop_logo
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                contentPadding = PaddingValues(0.dp),
                containerColor = Green
            ) {
                AllViewComponents.BackButton(
                    buttonLogoId = R.drawable.back_logo,
                    buttonLogoHeight = 40.dp,
                    buttonLogoWidth = 40.dp,
                    onClick = {}
                )
            }
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(LIGHT_GRAY)
                .padding(paddingValues)
        ){
            Column{
                StartScreenComponents.buttonWithLogoAndText(
                    buttonText = "Create New List",
                    buttonLogoId = R.drawable.controlled_inventory_logo,
                    buttonLogoHeight = 54.dp,
                    buttonLogoWidth = 46.dp,
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(50.dp))
                StartScreenComponents.buttonWithLogoAndText(
                    buttonText = "Stocks",
                    buttonLogoId = R.drawable.stocks_logo,
                    buttonLogoHeight = 50.dp,
                    buttonLogoWidth = 50.dp,
                    onClick = {}
                )
            }
        }
    }
}