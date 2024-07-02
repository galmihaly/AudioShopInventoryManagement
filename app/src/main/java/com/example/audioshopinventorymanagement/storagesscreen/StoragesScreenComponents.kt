package com.example.audioshopinventorymanagement.storagesscreen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioshopinventorymanagement.AllViewComponents
import com.example.audioshopinventorymanagement.R
import com.example.audioshopinventorymanagement.api.responses.StoragesDetails
import com.example.audioshopinventorymanagement.ui.theme.BLUE
import com.example.audioshopinventorymanagement.ui.theme.CustomFonts
import com.example.audioshopinventorymanagement.ui.theme.DARK_GRAY
import com.example.audioshopinventorymanagement.ui.theme.ERROR_RED
import com.example.audioshopinventorymanagement.ui.theme.GREEN
import com.example.audioshopinventorymanagement.ui.theme.LIGHT_GRAY
import com.example.audioshopinventorymanagement.utils.Formatter

object StoragesScreenComponents {

    @Composable
    fun StorageCard(
        cardNumber: Int,
        cardStorage: StoragesDetails,
        currentQuantityColor: Color,
        maxQuantityColor: Color,
        onClick: () -> Unit
    ){

        var currentColor = currentQuantityColor
        if (cardStorage.quantity!! == cardStorage.maxQuantity!!) {
            currentColor = ERROR_RED
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
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
                    key = stringResource(R.string.STORAGE_CARD_ROWTEXT_ID),
                    value = cardStorage.storageId!!,
                    color = Color.White,
                    keyTextWeight = 1.0f,
                    valueStringTextWeight = 1.0f
                )
                AllViewComponents.QuantityRowToCard(
                    key = stringResource(R.string.STORAGE_CARD_ROWTEXT_CURRENT),
                    value = cardStorage.quantity.toString() + " Piece",
                    valueColor = currentColor,
                    keyTextWeight = 1.0f,
                    valueStringTextWeight = 1.0f
                )
                AllViewComponents.QuantityRowToCard(
                    key = stringResource(R.string.STORAGE_CARD_ROWTEXT_MAX),
                    value = cardStorage.maxQuantity.toString() + " Piece",
                    valueColor = maxQuantityColor,
                    keyTextWeight = 1.0f,
                    valueStringTextWeight = 1.0f
                )
                AllViewComponents.TextRowToCard(
                    key = stringResource(R.string.STORAGE_CARD_ROWTEXT_NETTO),
                    value = Formatter.formatPrice(cardStorage.nettoValue.toString())!!,
                    color = Color.White,
                    keyTextWeight = 1.0f,
                    valueStringTextWeight = 1.0f
                )
                AllViewComponents.TextRowToCard(
                    key = stringResource(R.string.STORAGE_CARD_ROWTEXT_BRUTTO),
                    value = Formatter.formatPrice(cardStorage.bruttoValue.toString())!!,
                    color = Color.White,
                    keyTextWeight = 1.0f,
                    valueStringTextWeight = 1.0f
                )
                Box(modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth())
            }
        }
    }

    @Composable
    fun MatchesText(
        text: String
    ){
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = text,
                color = Color.White,
                fontFamily = CustomFonts.RobotoMono_Regular
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewComponent(){

    val data : MutableList<StoragesDetails> = ArrayList()
    data.add(StoragesDetails(
        storageId = "01",
        quantity = 50,
        maxQuantity = 100
    ))
    data.add(StoragesDetails(
        storageId = "02",
        quantity = 50,
        maxQuantity = 100
    ))

    Scaffold (
        topBar = {
            AllViewComponents.HeadLineWithText(
                headLineText = stringResource(R.string.STORAGE_TOPBAR_HEADLINE_TEXT),
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
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            ) {
                AllViewComponents.SearchField(
                    labelText = stringResource(R.string.STORAGE_SEARCHFIELD_LABEL_TEXT),
                    textFieldValue = "",
                    textChangeFunction = { },
                    deleteValueChange = { }
                )
                StoragesScreenComponents.MatchesText(
                    text = stringResource(R.string.ALL_MATCHES_TEXT) + "0"
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
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
}