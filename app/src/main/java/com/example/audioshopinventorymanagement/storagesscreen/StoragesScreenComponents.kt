package com.example.audioshopinventorymanagement.storagesscreen

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import com.example.audioshopinventorymanagement.authentication.responses.StoragesDetails
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY

object StoragesScreenComponents {

    @Composable
    fun CounterRowToCard(
        currentQuantity: String,
        currentQuantityColor : Color,
        maxQuantity: String,
        maxQuantityColor : Color
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = currentQuantity,
                color = currentQuantityColor,
                fontFamily = CustomFonts.RobotoMono_Regular,
                fontSize = 25.sp
            )
            Text(
                text = "/",
                color = Color.White,
                fontFamily = CustomFonts.RobotoMono_Regular,
                fontSize = 25.sp
            )
            Text(
                text = maxQuantity,
                color = maxQuantityColor,
                fontFamily = CustomFonts.RobotoMono_Regular,
                fontSize = 25.sp
            )
        }
    }

    @Composable
    fun TextRowToCard(
        text: String,
        color: Color
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, top = 10.dp, end = 20.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = text,
                color = color,
                fontFamily = CustomFonts.RobotoMono_Regular,
                fontSize = 15.sp
            )
        }
    }

    @Composable
    fun StorageCard(
        cardNumber: Int,
        cardStorage: StoragesDetails,
        currentQuantityColor: Color,
        maxQuantityColor: Color,
        onClick: () -> Unit
    ){
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

                CounterRowToCard(
                    currentQuantity = cardStorage.quantity.toString(),
                    currentQuantityColor = currentQuantityColor,
                    maxQuantity = cardStorage.maxQuantity.toString(),
                    maxQuantityColor = maxQuantityColor
                )
                TextRowToCard(
                    text = "Storage ID:",
                    color = Color.White
                )
                TextRowToCard(
                    text = cardStorage.storageId!!,
                    color = Color.White
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

    val data : MutableList<StoragesDetails> = ArrayList()
    data.add(StoragesDetails(
        storageId = "0100000000000000",
        quantity = 50,
        maxQuantity = 100
    ))
    data.add(StoragesDetails(
        storageId = "01",
        quantity = 20,
        maxQuantity = 100
    ))
    data.add(StoragesDetails(
        storageId = "01",
        quantity = 50,
        maxQuantity = 100
    ))
    data.add(StoragesDetails(
        storageId = "01",
        quantity = 50,
        maxQuantity = 100
    ))

    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = "Storages",
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                itemsIndexed(data) { index, storage ->
                    val pIndex = index + 1

                    StoragesScreenComponents.StorageCard(
                        cardNumber = pIndex,
                        cardStorage = storage,
                        currentQuantityColor = BLUE,
                        maxQuantityColor = GREEN,
                        onClick = { /*viewModel.onNavigateToCategoriesScreen(warehouse.id!!) */},
                    )
                }
            }
        }
    }
}