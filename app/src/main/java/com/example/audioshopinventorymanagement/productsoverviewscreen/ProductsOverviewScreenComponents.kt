package com.example.audioshopinventorymanagement.productsoverviewscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.api.responses.ProductDetails
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY
import com.example.audioshopinventorymanagement.utils.Formatter

object ProductsOverviewScreenComponents {

    @Composable
    fun ExtendedItemCard(
        cardNumber: Int,
        cardProduct: ProductDetails,
        expandedCard: Boolean
    ){

        var isExpanded by remember { mutableStateOf(expandedCard) }

        val cardBorderIndicatorColor = if (isExpanded) BLUE else GREEN
        val angle = if (isExpanded) 180f else 0f

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = {
                    isExpanded = !isExpanded
                })
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        delayMillis = 50,
                        easing = LinearOutSlowInEasing
                    )
                ),
            shape = RectangleShape,
            border = BorderStroke(2.dp, cardBorderIndicatorColor),
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
                            .background(cardBorderIndicatorColor),
                        contentAlignment = Alignment.Center,
                    ){
                        Text(
                            text = cardNumber.toString(),
                            color = Color.White,
                            fontFamily = CustomFonts.RobotoMono_Regular,
                            fontSize = 18.sp
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(30.dp),
                        contentAlignment = Alignment.Center,
                    ){
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .rotate(angle),
                            tint = Color.White
                        )
                    }
                }

                AllViewComponents.TextRowToCard(
                    key = stringResource(R.string.PRODUCT_BARCODE_TEXT1),
                    value = cardProduct.barcode!!,
                    color = GREEN,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )
                AllViewComponents.TextRowToCard(
                    key = stringResource(R.string.PRODUCT_ID_TEXT),
                    value = cardProduct.productId!!,
                    color = Color.White,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )
                AllViewComponents.TextRowToCard(
                    key = stringResource(R.string.PRODUCT_NAME_TEXT),
                    value = cardProduct.productName!!,
                    color = Color.White,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )

                if(isExpanded){
                    AllViewComponents.TextRowToCard(
                        key = stringResource(R.string.PRODUCT_TYPE_TEXT),
                        value = cardProduct.productType!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    AllViewComponents.TextRowToCard(
                        key = stringResource(R.string.PRODUCT_BASE_PRICE_TEXT),
                        value = Formatter.formatPrice(cardProduct.basePrice.toString())!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    AllViewComponents.TextRowToCard(
                        key = stringResource(R.string.PRODUCT_WHOLESALE_PRICE_TEXT),
                        value = Formatter.formatPrice(cardProduct.wholeSalePrice.toString())!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    AllViewComponents.TextRowToCard(
                        key = stringResource(R.string.PRODUCT_WAREHOUSE_ID_TEXT),
                        value = cardProduct.warehouseId!!,
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    AllViewComponents.TextRowToCard(
                        key = stringResource(R.string.PRODUCT_STORAGE_ID_TEXT),
                        value = cardProduct.storageId!!,
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    AllViewComponents.TextRowToCard(
                        key = stringResource(R.string.PRODUCT_DEVICE_ID_TEXT),
                        value = cardProduct.deviceId!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    AllViewComponents.TextRowToCard(
                        key = stringResource(R.string.PRODUCT_RECORDER_NAME_TEXT),
                        value = cardProduct.recorderName!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    AllViewComponents.TextRowToCard(
                        key = stringResource(R.string.PRODUCT_RECORDING_DATE_TEXT),
                        value = cardProduct.recordingDate.toString(),
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                }
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
                headLineText = stringResource(R.string.PRODUCT_OVERVIEW_TOPBAR_HEADLINE_TEXT),
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
                    labelText = stringResource(R.string.PRODUCT_BARCODE_TEXT2),
                    textFieldValue = "",
                    textChangeFunction = {  },
                    deleteValueChange = {  }
                )
                AllViewComponents.MatchesText(
                    text = stringResource(R.string.ALL_MATCHES_TEXT) + "0"
                )
                ProductsOverviewScreenComponents.ExtendedItemCard(
                    cardNumber = 1,
                    cardProduct = ProductDetails(),
                    expandedCard = false
                )
            }
        }
    }
}