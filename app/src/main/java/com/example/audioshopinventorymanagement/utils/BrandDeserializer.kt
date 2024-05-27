package com.example.audioshopinventorymanagement.utils

import android.util.Log
import com.example.audioshopinventorymanagement.authentication.responses.BrandDetails
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type

class BrandDeserializer<T>(private val responseType : Class<T>) : JsonDeserializer<T> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): T {
       val jsonObject = json.asJsonObject

        return try {
            val response = responseType.getDeclaredConstructor().newInstance()

            if (response is BrandListResponse) {
                setFields(response, jsonObject, context)
            }

            response
        } catch (e: InstantiationException) {
            throw RuntimeException("Unable to create response object.", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Unable to create response object.", e)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("Unable to create response object.", e)
        }
    }

    private fun setFields(
        response: BrandListResponse,
        jsonObject: JsonObject,
        context: JsonDeserializationContext?
    ) {
        response.timeStamp = jsonObject.get("timestamp").toString()
        response.statusCode = jsonObject.get("status_code").toString().toInt()
        response.messageType = jsonObject.get("message_type").toString()
        response.messageBody = jsonObject.get("message_body").toString()

        val brandDetails = jsonObject.get("brands")

        Log.e("fasz", brandDetails.toString())

        when {

            brandDetails.isJsonArray -> response.brandDetails = context?.deserialize(
                brandDetails,
                object : TypeToken<List<BrandDetails>>() {}.type
            ) ?: emptyList()

            brandDetails.isJsonObject -> {
                val singleBrandDetails = context?.deserialize<BrandDetails>(brandDetails, BrandDetails::class.java)
                response.brandDetails = if (singleBrandDetails != null) {
                    listOf(singleBrandDetails)
                }
                else {
                    emptyList()
                }
            }
            else -> response.brandDetails = null
        }
    }
}