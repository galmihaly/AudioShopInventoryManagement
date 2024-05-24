package com.example.audioshopinventorymanagement.warehousesscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

object WareHousesScreenComponents {

    @Composable
    fun WareHouseCard(cardNumber: Int, textValues: String, onClick: () -> Unit){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        delayMillis = 50,
                        easing = LinearOutSlowInEasing
                    )
                ),
            onClick = onClick,
            shape = RectangleShape,
            border = BorderStroke(2.dp, GREEN),
            colors = CardColors(
                containerColor = DARK_GRAY,
                contentColor = DARK_GRAY,
                disabledContainerColor = DARK_GRAY,
                disabledContentColor = DARK_GRAY
            )
        ) {
            Column(
                modifier = Modifier.padding(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(GREEN),
                        contentAlignment = Alignment.Center,
                    ){
                        Text(
                            text = cardNumber.toString(),
                            color = Color.White,
                            fontFamily = CustomFonts.RobotoMono_Regular,
                            fontSize = 18.sp
                        )
                    }
                }

                AllViewComponents.TextRowToCard(
                    key = "ID:",
                    value = "DE01",
                    color = GREEN,
                    keyTextWeight = 1.1f,
                    valueStringTextWeight = 0.9f
                )
                AllViewComponents.TextRowToCard(
                    key = "Name:",
                    value = "Audio Shop Hungary Central WareHouse",
                    color = Color.White,
                    keyTextWeight = 1.1f,
                    valueStringTextWeight = 0.9f
                )

                Spacer(modifier = Modifier.height(20.dp))

                AllViewComponents.TextRowToCard(
                    key = "Current Stock Capacity:",
                    value = "0" + " Piece",
                    color = Color.White,
                    keyTextWeight = 1.1f,
                    valueStringTextWeight = 0.9f
                )
                AllViewComponents.TextRowToCard(
                    key = "Max Stock Capacity:",
                    value = "50" + " Piece",
                    color = Color.White,
                    keyTextWeight = 1.1f,
                    valueStringTextWeight = 0.9f
                )
                AllViewComponents.TextRowToCard(
                    key = "Stocks Value:",
                    value = "58.260" + " Ft",
                    color = Color.White,
                    keyTextWeight = 1.1f,
                    valueStringTextWeight = 0.9f
                )
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
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
            AllViewComponents.HeadLineWithText(
                headLineText = "WareHouse List",
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
                        onClick = { }
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
                    .padding(horizontal = 10.dp, vertical = 20.dp),
            ) {
                WareHousesScreenComponents.WareHouseCard(
                    cardNumber = 1,
                    textValues = "",
                    onClick = {}
                )
            }
        }
    }
}