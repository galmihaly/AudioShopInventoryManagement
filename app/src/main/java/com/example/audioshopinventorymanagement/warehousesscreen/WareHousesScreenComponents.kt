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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.audioshopinventorymanagement.api.responses.StoragesDetails
import com.example.audioshopinventorymanagement.api.responses.WarehouseDetails
import com.example.audioshopinventorymanagement.storagesscreen.StoragesScreenComponents
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY
import com.example.audioshopinventorymanagement.utils.Formatter

object WareHousesScreenComponents {
    @Composable
    fun WareHouseCard(
        cardNumber: Int,
        cardWarehouse: WarehouseDetails,
        currentQuantityColor: Color,
        maxQuantityColor: Color,
        onClick: () -> Unit
    ){
        var currentColor = currentQuantityColor
        if (cardWarehouse.currentStockCapacity!! >= cardWarehouse.stockMaxCapacity!!) {
            currentColor = ERROR_RED
        }

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
                    value = cardWarehouse.warehouseId!!,
                    color = GREEN,
                    keyTextWeight = 0.5f,
                    valueStringTextWeight = 1.5f
                )
                AllViewComponents.TextRowToCard(
                    key = "Name:",
                    value = cardWarehouse.name!!,
                    color = Color.White,
                    keyTextWeight = 0.5f,
                    valueStringTextWeight = 1.5f
                )

                Spacer(modifier = Modifier.height(20.dp))

                AllViewComponents.QuantityRowToCard(
                    key = "Current Capacity:",
                    value = cardWarehouse.currentStockCapacity.toString() + " Piece",
                    valueColor = currentColor,
                    keyTextWeight = 1.1f,
                    valueStringTextWeight = 0.9f
                )
                AllViewComponents.QuantityRowToCard(
                    key = "Max Capacity:",
                    value = cardWarehouse.stockMaxCapacity.toString() + " Piece",
                    valueColor = maxQuantityColor,
                    keyTextWeight = 1.1f,
                    valueStringTextWeight = 0.9f
                )
                AllViewComponents.TextRowToCard(
                    key = "Stocks Netto Value:",
                    value = Formatter.formatPrice(cardWarehouse.nettoValue.toString())!!,
                    color = Color.White,
                    keyTextWeight = 1.1f,
                    valueStringTextWeight = 0.9f
                )
                AllViewComponents.TextRowToCard(
                    key = "Stocks Brutto Value:",
                    value = Formatter.formatPrice(cardWarehouse.bruttoValue.toString())!!,
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
fun PreviewComponent(){

    val data : MutableList<WarehouseDetails> = ArrayList()
    data.add(
        WarehouseDetails()
    )
    data.add(
        WarehouseDetails()
    )

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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                itemsIndexed(data) { index, warehouse ->
                    val pIndex = index + 1

                    WareHousesScreenComponents.WareHouseCard(
                        cardNumber = pIndex,
                        cardWarehouse = warehouse,
                        currentQuantityColor = BLUE,
                        maxQuantityColor = GREEN,
                        onClick = {}
                    )
                }
            }
        }
    }
}