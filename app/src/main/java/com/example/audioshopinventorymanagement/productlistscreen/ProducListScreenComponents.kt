package com.example.audioshopinventorymanagement.productlistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.room.entities.ProductEntity
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

object ProducListScreenComponents {

    @Composable
    fun MatchesTextAndSendButton(
        text: String,
        sendButtonFunction: () -> Unit,
        buttonLogoId: Int,
        buttonLogoHeight: Dp,
        buttonLogoWidth: Dp,
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth().height(80.dp)
                .padding(vertical = 15.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.wrapContentWidth().fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = text,
                        color = Color.White,
                        fontFamily = CustomFonts.RobotoMono_Regular
                    )
                }
                Box(
                    modifier = Modifier.wrapContentWidth().fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Button(
                        onClick = sendButtonFunction,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BLUE
                        ),
                        modifier = Modifier
                            .width(100.dp)
                            .height(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = buttonLogoId),
                            contentDescription = "",
                            modifier = Modifier
                                .height(buttonLogoHeight)
                                .width(buttonLogoWidth)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewComponent(){
    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = "New Products List",
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentPadding = PaddingValues(0.dp),
                containerColor = LIGHT_GRAY
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Absolute.Center
                ) {
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.back_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = GREEN,
                        onClick = {}
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.add_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = BLUE,
                        onClick = {}
                    )
                    AllViewComponents.NavigationButtons(
                        buttonLogoId = R.drawable.delete_list_logo,
                        buttonLogoHeight = 40.dp,
                        buttonLogoWidth = 40.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        backgroundColor = ERROR_RED,
                        onClick = {}
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LIGHT_GRAY)
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
            ) {
                AllViewComponents.SearchField(
                    labelText = "Search",
                    textFieldValue = "",
                    textChangeFunction = {},
                    deleteValueChange = {}
                )
                ProducListScreenComponents.MatchesTextAndSendButton(
                    text = "All Matches: " + "12345",
                    sendButtonFunction = {},
                    buttonLogoId = R.drawable.send_cube,
                    buttonLogoWidth = 40.dp,
                    buttonLogoHeight = 40.dp
                )
                AllViewComponents.ItemCard(
                    cardNumber = 1,
                    cardProduct = ProductEntity(),
                    modifyButtonLogo = R.drawable.modify_logo,
                    deleteButtonLogo = R.drawable.delete_x_logo,
                    modifyCardFunction = {},
                    deleteCardFunction = {  },
                    expandedCard = false
                )
            }
        }
    }
}