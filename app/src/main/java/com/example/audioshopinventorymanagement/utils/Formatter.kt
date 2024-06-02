package com.example.audioshopinventorymanagement.utils

import java.lang.StringBuilder
import java.util.regex.Pattern
import javax.inject.Inject

class Formatter {

    companion object{
        private val PRODUCTNAME_SEPARATOR = " "
        private val PRICE_SEPARATOR = '.'
        private val PRICE_FORINT = "Ft"

        private val  NUMERIC_PATTERN: Pattern = Pattern.compile(
            "-?[0-9]+(\\.[0-9]+)?"
        )

        fun formatPrice(price: String) : String? {
            if(price == "") return null

            val sb = StringBuilder(price)

            var result = ""
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
                .append(PRODUCTNAME_SEPARATOR)
                .append(PRICE_FORINT)
                .toString()
        }
    }
}