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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.ui.theme.Blue
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.Dark_Gray
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN

object AllViewComponents {
    @Composable
    fun HeadLineWithTextAndLogo(headLineText: String, headLineLogo: Int) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Dark_Gray),
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
                .background(Dark_Gray),
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
    fun SearchField(value: String, textFieldValue: String, onValueChange: () -> Unit, deleteValueChange: () -> Unit){

        var textFieldState by remember { mutableStateOf(textFieldValue) }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            value = textFieldState,
            onValueChange = { textFieldState = it },
            label = {
                Text(
                    text = value,
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
            )
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
    fun TextRowToCard(key: String, value: String, color: Color, keyTextWeight: Float, valueStringTextWeight: Float){
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

    // ide kell paraméterként majd megadni viewstate data objectet, hogy abból beillesszük az adatokat a megfelelő helyre
    @Composable
    fun ItemCard(
        cardNumber: Int,
        modifyButtonLogo: Int,
        modifyCardFunction: () -> Unit,
        expandedCard: Boolean
    ){

        var isExpanded by remember { mutableStateOf(expandedCard) }

        val cardBorderIndicatorColor = if (isExpanded) Blue else GREEN
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
                containerColor = Dark_Gray,
                contentColor = Dark_Gray,
                disabledContainerColor = Dark_Gray,
                disabledContentColor = Dark_Gray
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
                    value = "123456789",
                    color = GREEN,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )
                TextRowToCard(
                    key = "Product ID:",
                    value = "01-01-0001",
                    color = Color.White,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )
                TextRowToCard(
                    key = "Product Name:",
                    value = "Sennheiser HD 560s",
                    color = Color.White,
                    keyTextWeight = 0.9f,
                    valueStringTextWeight = 1.1f
                )

                if(isExpanded){
                    TextRowToCard(
                        key = "Product Type:",
                        value = "Headphone",
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "Base Price:",
                        value = "58.260" + " Ft",
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "WholeSale Price:",
                        value = "73.990" + " Ft",
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextRowToCard(
                        key = "Device ID:",
                        value = "ZTC-1",
                        color = Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "WareHouse ID:",
                        value = "DE01",
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "Stock ID:",
                        value = "DE01-0001",
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "Recorder Name:",
                        value = "Tóth Elek",
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )
                    TextRowToCard(
                        key = "Recording Date:",
                        value = "2024-04-17 12:13:58",
                        Color.White,
                        keyTextWeight = 0.9f,
                        valueStringTextWeight = 1.1f
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center,
                    ){
                        Row {
                            Button(
                                onClick = modifyCardFunction,
                                modifier = Modifier.wrapContentSize(),
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Blue
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


@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewComponent(){
    AllViewComponents.HeadLineWithTextAndLogo(
        headLineText = "Inventory Management",
        headLineLogo = R.drawable.audioshop_logo
    )
}