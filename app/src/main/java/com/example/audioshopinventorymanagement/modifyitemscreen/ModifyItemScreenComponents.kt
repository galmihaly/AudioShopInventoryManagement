package com.example.audioshopinventorymanagement.modifyitemscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED

object ModifyItemScreenComponents {
    @Composable
    fun ErrorDialog(
        isShowErrorDialog: Boolean,
        dialogText: String,
        readyButtonText: String,
        cancelButtonText: String,
        dialogDismissFunction: () -> Unit,
        navigateFunction: () -> Unit
    ) {
        if(isShowErrorDialog){
            Dialog(
                onDismissRequest = dialogDismissFunction
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = DARK_GRAY,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 10.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .border(
                                width = 0.8.dp,
                                color = ERROR_RED,
                                shape= RoundedCornerShape(16.dp)
                            )
                    ){
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = dialogText,
                                    fontSize = 18.sp,
                                    color = ERROR_RED,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 20.dp, horizontal = 40.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = navigateFunction,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = BLUE
                                    ),
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = readyButtonText,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontFamily = CustomFonts.RobotoMono_Bold
                                    )
                                }
                                Button(
                                    onClick = dialogDismissFunction,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = BLUE
                                    ),
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = cancelButtonText,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontFamily = CustomFonts.RobotoMono_Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewComponent(){
    ModifyItemScreenComponents.ErrorDialog(
        isShowErrorDialog = true,
        dialogText = "Hello",
        readyButtonText = "YES",
        cancelButtonText = "NO",
        dialogDismissFunction = {},
        navigateFunction = {}
    )
}
