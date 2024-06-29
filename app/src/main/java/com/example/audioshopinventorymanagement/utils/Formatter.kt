package com.example.audioshopinventorymanagement.utils

import android.content.res.Resources
import com.example.audioshopinventorymanagement.R
import java.lang.StringBuilder
import java.util.regex.Pattern

class Formatter {
    companion object{
        private val PRODUCT_NAME_SEPARATOR = Resources.getSystem().getString(R.string.PRODUCT_NAME_SEPARATOR)
        private val PRICE_SEPARATOR = Resources.getSystem().getString(R.string.PRICE_SEPARATOR)
        private val PRODUCT_ID_SEPARATOR = Resources.getSystem().getString(R.string.PRODUCT_ID_SEPARATOR)
        private val PRICE_FORINT = Resources.getSystem().getString(R.string.PRICE_FORINT)

        private val  NUMERIC_PATTERN: Pattern = Pattern.compile(
            "-?[0-9]+(\\.[0-9]+)?"
        )

        fun formatPrice(price: String) : String? {
            if(price == "") return null

            val sb = StringBuilder(price)

            if(NUMERIC_PATTERN.matcher(sb.toString()).matches()){
                if(sb.length in 4..6){
                    sb
                        .insert(sb.length - 3, PRICE_SEPARATOR)
                        .toString();
                }
                else if(sb.length in 7..9){
                    sb
                        .insert(sb.length - 3, PRICE_SEPARATOR)
                        .insert(sb.length - 7, PRICE_SEPARATOR)
                        .toString();
                }
                else if(sb.length in 12..14){
                    sb
                        .insert(sb.length - 3, PRICE_SEPARATOR)
                        .insert(sb.length - 7, PRICE_SEPARATOR)
                        .insert(sb.length - 11, PRICE_SEPARATOR)
                        .toString();
                }
            }
            return sb
                .append(PRODUCT_NAME_SEPARATOR)
                .append(PRICE_FORINT)
                .toString()
        }

        fun createProductId(brandId: String, modelId: String, categoryId: String): String {
            return StringBuilder()
                .append(brandId)
                .append(PRODUCT_ID_SEPARATOR)
                .append(categoryId)
                .append(PRODUCT_ID_SEPARATOR)
                .append(modelId)
                .toString()
        }

        fun createProductName(brand : String, model : String) : String{
            return StringBuilder()
                .append(brand)
                .append(PRODUCT_NAME_SEPARATOR)
                .append(model)
                .toString()
        }
    }
}