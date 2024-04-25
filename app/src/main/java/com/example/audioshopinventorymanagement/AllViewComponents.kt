package com.example.audioshopinventorymanagement

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.MutableState
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
import com.example.audioshopinventorymanagement.ui.theme.Error_Red
import com.example.audioshopinventorymanagement.ui.theme.Green

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
                    color = Green,
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
                    color = Green,
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
            colors = ButtonDefaults.buttonColors(containerColor = Green),
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
                    color = Green,
                    fontFamily = CustomFonts.RobotoMono_Regular
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Green
                )
            },
            trailingIcon = {
                IconButton(onClick = deleteValueChange) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Error_Red
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Green,
                focusedLabelColor = Green,
                cursorColor = Green,
                focusedTextColor = Green,
                unfocusedTextColor = Green,
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
    private fun TextRowToCard(text: String, text2: String, color: Color){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
                    .padding(start = 30.dp),
            ){
                Text(
                    text = text,
                    color = Color.White,
                    fontFamily = CustomFonts.RobotoMono_Regular,
                    fontSize = 13.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.1f)
                    .padding(end = 30.dp)
            ){
                Text(
                    text = text2,
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
        deleteButtonLogo: Int,
        modifyButtonLogo: Int,
        cardIndicatorLogo: Int,
        deleteCardFunction: () -> Unit,
        modifyCardFunction: () -> Unit,
        expandedCardFunction: Boolean
    ){

        var expanded by remember { mutableStateOf(expandedCardFunction) }
        var angle = 0f

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = {
                    expanded = !expanded
                    angle = if(expanded) 180f else 0f
                })
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        delayMillis = 50,
                        easing = LinearOutSlowInEasing
                    )
                ),
            shape = RectangleShape,
            border = BorderStroke(2.dp, Green),
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
                            .background(Green),
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
                            .size(30.dp)
                            .background(Error_Red),
                        contentAlignment = Alignment.Center,
                    ){
                        IconButton(
                            onClick = deleteCardFunction,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = deleteButtonLogo),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = Color.White
                            )
                        }
                    }
                }

                TextRowToCard(text = "Barcode:", text2 = "123456789", Green)
                TextRowToCard(text = "Product ID:", text2 = "01-01-0001", Color.White)
                TextRowToCard(text = "Product Name:", text2 = "Sennheiser HD 560s", Color.White)

                if(expanded){
                    TextRowToCard(text = "Product Type:", text2 = "Headphone", Color.White)
                    TextRowToCard(text = "Base Price:", text2 = "58.260" + " Ft", Color.White)
                    TextRowToCard(text = "WholeSale Price:", text2 = "73.990" + " Ft", Color.White)
                    Spacer(modifier = Modifier.height(30.dp))
                    TextRowToCard(text = "Device ID:", text2 = "ZTC-1", Color.White)
                    TextRowToCard(text = "WareHouse ID:", text2 = "DE01", Color.White)
                    TextRowToCard(text = "Stock ID:", text2 = "DE01-0001", Color.White)
                    TextRowToCard(text = "Recorder Name:", text2 = "Tóth Elek", Color.White)
                    TextRowToCard(text = "Recording Date:", text2 = "2024-04-17 12:13:58", Color.White)
                }

                Column{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(Green)
                        ){
                            IconButton(
                                onClick = modifyCardFunction,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    painter = painterResource(id = cardIndicatorLogo),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .rotate(angle),
                                    tint = Color.White
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(Blue)
                        ){
                            IconButton(
                                onClick = modifyCardFunction,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    painter = painterResource(id = modifyButtonLogo),
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp),
                                    tint = Color.White
                                )
                            }
                        }
                    }

//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight(),
//                    contentAlignment = Alignment.BottomEnd,
//                ){
//                    Box(
//                        modifier = Modifier
//                            .size(30.dp)
//                            .background(Blue)
//                    ){
//                        IconButton(
//                            onClick = modifyCardFunction,
//                            modifier = Modifier.fillMaxSize()
//                        ) {
//                            Icon(
//                                painter = painterResource(id = modifyButtonLogo),
//                                contentDescription = null,
//                                modifier = Modifier.size(18.dp),
//                                tint = Color.White
//                            )
//                        }
//                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewComponent(){
    AllViewComponents.ItemCard(
        cardNumber = 1,
        deleteButtonLogo = R.drawable.card_delete_logo,
        modifyButtonLogo = R.drawable.modify_logo,
        cardIndicatorLogo = R.drawable.bottom_arrow_logo,
        deleteCardFunction = {},
        modifyCardFunction = {},
        expandedCardFunction = false
    )
}