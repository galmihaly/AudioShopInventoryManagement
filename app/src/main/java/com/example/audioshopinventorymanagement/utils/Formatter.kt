package com.example.audioshopinventorymanagement.utils

import java.lang.StringBuilder
import java.util.regex.Pattern
import javax.inject.Inject

class Formatter @Inject constructor(
    private val sb: StringBuilder
) {

    private val PRODUCTID_SEPARATOR = '-'
    private val PRODUCTNAME_SEPARATOR = " "
    private val PRICE_SEPARATOR = '.'
    private val PRICE_FORINT = "Ft"

    private val  NUMERIC_PATTERN: Pattern = Pattern.compile(
        "-?[0-9]+(\\.[0-9]+)?"
    )

    //ez még nem működik jól
    fun formatPrice(price: String) : String? {
        if(price == "") return null

        var result = ""
        if(NUMERIC_PATTERN.matcher(price).matches()){
            val s = sb.append(price)
            if(s.length > 3){
                result = s
                    .insert(2, PRICE_SEPARATOR)
                    .toString()
            }
            else if(s.length > 6){
                result = s
                    .insert(2, PRICE_SEPARATOR)
                    .insert(4, PRICE_SEPARATOR)
                    .toString()
            }
        }
        return sb
            .append(result)
            .append(PRODUCTNAME_SEPARATOR)
            .append(PRICE_FORINT)
            .toString()
    }

    fun createProductId(brandId: String, modelId: String, categoryId: String): String {
        return sb
            .append(brandId)
            .append(PRODUCTID_SEPARATOR)
            .append(categoryId)
            .append(PRODUCTID_SEPARATOR)
            .append(modelId)
            .toString()
    }

    fun createProductName(brand : String, model : String) : String{
        return StringBuilder()
            .append(brand)
            .append(PRODUCTNAME_SEPARATOR)
            .append(model)
            .toString()
    }
}