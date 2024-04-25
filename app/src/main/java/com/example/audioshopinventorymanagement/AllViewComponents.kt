package com.example.audioshopinventorymanagement

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.Dark_Gray
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
}