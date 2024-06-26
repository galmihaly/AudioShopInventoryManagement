package com.example.audioshopinventorymanagement

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.room.entities.ProductEntity
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.utils.Formatter

object AllViewComponents {
    @Composable
    fun HeadLineWithTextAndLogo(headLineText: String, headLineLogo: Int) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DARK_GRAY),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 10.dp)
                ){
                    Image(
                        painter = painterResource(headLineLogo),
                        modifier = Modifier.height(80.dp),
                        contentDescription = null
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 20.dp)
                ){
                    Text(
                        text = headLineText,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = CustomFonts.RobotoMono_Bold,
                    )
                }
                HorizontalDivider(
                    color = GREEN,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }

    @Composable
    fun HeadLineWithText(headLineText: String) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DARK_GRAY),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ){
                    Text(
                        text = headLineText,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = CustomFonts.RobotoMono_Bold,
                    )
                }
                HorizontalDivider(
                    color = GREEN,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }

    @Composable
    fun BackButton(buttonLogoId: Int, buttonLogoHeight: Dp, buttonLogoWidth: Dp, onClick: () -> Unit){
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = ButtonDefaults.buttonColors(containerColor = GREEN),
            shape = RectangleShape
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

    @Composable
    fun NavigationButtons(
        buttonLogoId: Int,
        buttonLogoHeight: Dp,
        buttonLogoWidth: Dp,
        modifier: Modifier,
        backgroundColor: Color,
        onClick: () -> Unit){
        Button(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
            shape = RectangleShape
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

    @Composable
    fun SearchField(
        labelText: String,
        textFieldValue: String,
        textChangeFunction : (String) -> Unit,
        deleteValueChange: () -> Unit)
    {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            value = textFieldValue,
            onValueChange = textChangeFunction,
            label = {
                Text(
                    text = labelText,
                    color = GREEN,
                    fontFamily = CustomFonts.RobotoMono_Regular
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = GREEN
                )
            },
            trailingIcon = {
                IconButton(onClick = deleteValueChange) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = ERROR_RED
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GREEN,
                unfocusedBorderColor = GREEN,
                focusedLabelColor = GREEN,
                cursorColor = GREEN,
                focusedTextColor = GREEN,
                unfocusedTextColor = GREEN,
                unfocusedLabelColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            singleLine = true
        )
    }

    @Composable
    fun MatchesText(text: String){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        ){
            Text(
                text = text,
                color = Color.White,
                fontFamily = CustomFonts.RobotoMono_Regular
            )
        }
    }

    @Composable
    fun TextRowToCard(
        key: String,
        value: String,
        color: Color,
        keyTextWeight: Float,
        valueStringTextWeight: Float
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(keyTextWeight)
                    .padding(start = 30.dp),
            ){
                Text(
                    text = key,
                    color = Color.White,
                    fontFamily = CustomFonts.RobotoMono_Regular,
                    fontSize = 13.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(valueStringTextWeight)
                    .padding(end = 30.dp)
            ){
                Text(
                    text = value,
                    color = color,
                    fontFamily = CustomFonts.RobotoMono_Regular,
                    fontSize = 13.sp
                )
            }
        }
    }

    @Composable
    fun QuantityRowToCard(
        key: String,
        value: String,
        valueColor : Color,
        keyTextWeight: Float,
        valueStringTextWeight: Float
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(keyTextWeight)
                    .padding(start = 30.dp),
            ){
                Text(
                    text = key,
                    color = Color.White,
                    fontFamily = CustomFonts.RobotoMono_Regular,
                    fontSize = 13.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(valueStringTextWeight)
                    .padding(end = 30.dp)
            ){
                Text(
                    text = value,
                    color = valueColor,
                    fontFamily = CustomFonts.RobotoMono_Regular,
                    fontSize = 13.sp
                )
            }
        }
    }

    @Composable
    fun ItemCard(
        cardNumber: Int,
        cardProduct: ProductEntity,
        modifyButtonLogo: Int,
        deleteButtonLogo: Int,
        modifyCardFunction: () -> Unit,
        deleteCardFunction: () -> Unit,
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

                TextRowToCard(
                    key = "Barcode:",
                    value = cardProduct.barcode!!,
                    color = GREEN,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )
                TextRowToCard(
                    key = "Product ID:",
                    value = cardProduct.productId!!,
                    color = Color.White,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )
                TextRowToCard(
                    key = "Product Name:",
                    value = cardProduct.productName!!,
                    color = Color.White,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )

                if(isExpanded){
                    TextRowToCard(
                        key = "Product Type:",
                        value = cardProduct.categoryName!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "Base Price:",
                        value = Formatter.formatPrice(cardProduct.basePrice.toString())!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "WholeSale Price:",
                        value = Formatter.formatPrice(cardProduct.wholeSalePrice.toString())!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextRowToCard(
                        key = "WareHouse ID:",
                        value = cardProduct.warehouseId!!,
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "Storage ID:",
                        value = cardProduct.storageId!!,
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "Recorder Name:",
                        value = cardProduct.recorderName!!,
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "Device ID:",
                        value = cardProduct.deviceId!!,
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(30.dp)
                    ){
                        Row (
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = modifyCardFunction,
                                modifier = Modifier.wrapContentSize(),
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = BLUE
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = modifyButtonLogo),
                                    contentDescription = "Modify",
                                    modifier = Modifier.size(18.dp),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(
                                    text = "Modify",
                                    color = Color.White,
                                    fontFamily = CustomFonts.RobotoMono_Regular,
                                    fontSize = 18.sp
                                )
                            }
                            Button(
                                onClick = deleteCardFunction,
                                modifier = Modifier.wrapContentSize(),
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = ERROR_RED
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = deleteButtonLogo),
                                    contentDescription = "Delete",
                                    modifier = Modifier.size(18.dp),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(
                                    text = "Delete",
                                    color = Color.White,
                                    fontFamily = CustomFonts.RobotoMono_Regular,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
                else{
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}