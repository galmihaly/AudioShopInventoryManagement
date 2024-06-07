package com.example.audioshopinventorymanagement.utils

import com.example.audioshopinventorymanagement.authentication.responses.BaseResponse
import com.example.audioshopinventorymanagement.authentication.responses.BrandDetails
import com.example.audioshopinventorymanagement.authentication.responses.BrandListResponse
import com.example.audioshopinventorymanagement.authentication.responses.CategoryDetails
import com.example.audioshopinventorymanagement.authentication.responses.CategoryListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ModelDetails
import com.example.audioshopinventorymanagement.authentication.responses.ModelListResponse
import com.example.audioshopinventorymanagement.authentication.responses.ProductDetails
import com.example.audioshopinventorymanagement.authentication.responses.ProductListResponse
import com.example.audioshopinventorymanagement.authentication.responses.StoragesDetails
import com.example.audioshopinventorymanagement.authentication.responses.StoragesListResponse
import com.example.audioshopinventorymanagement.authentication.responses.WarehouseDetails
import com.example.audioshopinventorymanagement.authentication.responses.WarehouseListResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ApiResponseDeserializer<T>(private val responseType : Class<T>) : JsonDeserializer<T> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): T {
        val jsonObject = json.asJsonObject
        return try {
            val response = responseType.getDeclaredConstructor().newInstance()

            when (response) {
                is BrandListResponse -> {
                    setBrandFields(response, jsonObject, context)
                }
                is CategoryListResponse -> {
                    setCategoryFields(response, jsonObject, context)
                }
                is ModelListResponse -> {
                    setModelFields(response, jsonObject, context)
                }
                is WarehouseListResponse -> {
                    setWarehouseFields(response, jsonObject, context)
                }
                is StoragesListResponse -> {
                    setStoragesFields(response, jsonObject, context)
                }
                is ProductListResponse -> {
                    setProductListFields(response, jsonObject, context)
                }
                is BaseResponse -> {
                    setBaseResponseFields(response, jsonObject)
                }
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

    private fun setBaseResponseFields(
        response: BaseResponse,
        jsonObject: JsonObject?
    ) {
        if (jsonObject != null) {
            response.timeStamp = jsonObject.get("timestamp").toString()
            response.statusCode = jsonObject.get("status_code").toString().toInt()
            response.messageType = jsonObject.get("message_type").toString()
            response.messageBody = jsonObject.get("message_body").toString()
        }
    }

    private fun setBrandFields(
        response: BrandListResponse,
        jsonObject: JsonObject,
        context: JsonDeserializationContext?
    ) {
        response.timeStamp = jsonObject.get("timestamp").toString()
        response.statusCode = jsonObject.get("status_code").toString().toInt()
        response.messageType = jsonObject.get("message_type").toString()
        response.messageBody = jsonObject.get("message_body").toString()

        val brandDetails = jsonObject.get("brands")
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

    private fun setCategoryFields(
        response: CategoryListResponse,
        jsonObject: JsonObject,
        context: JsonDeserializationContext?
    ) {
        response.timeStamp = jsonObject.get("timestamp").toString()
        response.statusCode = jsonObject.get("status_code").toString().toInt()
        response.messageType = jsonObject.get("message_type").toString()
        response.messageBody = jsonObject.get("message_body").toString()

        val categoryDetails = jsonObject.get("categories")
        when {

            categoryDetails.isJsonArray -> response.categoryDetails = context?.deserialize(
                categoryDetails,
                object : TypeToken<List<CategoryDetails>>() {}.type
            ) ?: emptyList()

            categoryDetails.isJsonObject -> {
                val singleCategoryDetails = context?.deserialize<CategoryDetails>(categoryDetails, CategoryDetails::class.java)
                response.categoryDetails = if (singleCategoryDetails != null) {
                    listOf(singleCategoryDetails)
                }
                else {
                    emptyList()
                }
            }
            else -> response.categoryDetails = null
        }
    }

    private fun setModelFields(
        response: ModelListResponse,
        jsonObject: JsonObject,
        context: JsonDeserializationContext?
    ) {
        response.timeStamp = jsonObject.get("timestamp").toString()
        response.statusCode = jsonObject.get("status_code").toString().toInt()
        response.messageType = jsonObject.get("message_type").toString()
        response.messageBody = jsonObject.get("message_body").toString()

        val modelDetails = jsonObject.get("models")
        when {

            modelDetails.isJsonArray -> response.modelDetails = context?.deserialize(
                modelDetails,
                object : TypeToken<List<ModelDetails>>() {}.type
            ) ?: emptyList()

            modelDetails.isJsonObject -> {
                val singleModelDetails = context?.deserialize<ModelDetails>(modelDetails, ModelDetails::class.java)
                response.modelDetails = if (singleModelDetails != null) {
                    listOf(singleModelDetails)
                }
                else {
                    emptyList()
                }
            }
            else -> response.modelDetails = null
        }
    }

    private fun setWarehouseFields(
        response: WarehouseListResponse,
        jsonObject: JsonObject,
        context: JsonDeserializationContext?
    ) {
        response.timeStamp = jsonObject.get("timestamp").toString()
        response.statusCode = jsonObject.get("status_code").toString().toInt()
        response.messageType = jsonObject.get("message_type").toString()
        response.messageBody = jsonObject.get("message_body").toString()

        val warehouseDetails = jsonObject.get("warehouses")
        when {

            warehouseDetails.isJsonArray -> response.warehouseDetails = context?.deserialize(
                warehouseDetails,
                object : TypeToken<List<WarehouseDetails>>() {}.type
            ) ?: emptyList()

            warehouseDetails.isJsonObject -> {
                val singleWarehouseDetails = context?.deserialize<WarehouseDetails>(warehouseDetails, WarehouseDetails::class.java)
                response.warehouseDetails = if (singleWarehouseDetails != null) {
                    listOf(singleWarehouseDetails)
                }
                else {
                    emptyList()
                }
            }
            else -> response.warehouseDetails = null
        }
    }

    private fun setStoragesFields(
        response: StoragesListResponse,
        jsonObject: JsonObject,
        context: JsonDeserializationContext?
    ) {
        response.timeStamp = jsonObject.get("timestamp").toString()
        response.statusCode = jsonObject.get("status_code").toString().toInt()
        response.messageType = jsonObject.get("message_type").toString()
        response.messageBody = jsonObject.get("message_body").toString()

        val storagesDetails = jsonObject.get("storages")
        when {

            storagesDetails.isJsonArray -> response.storagesDetails = context?.deserialize(
                storagesDetails,
                object : TypeToken<List<StoragesDetails>>() {}.type
            ) ?: emptyList()

            storagesDetails.isJsonObject -> {
                val singleStoragesDetails = context?.deserialize<StoragesDetails>(storagesDetails, StoragesDetails::class.java)
                response.storagesDetails = if (singleStoragesDetails != null) {
                    listOf(singleStoragesDetails)
                }
                else {
                    emptyList()
                }
            }
            else -> response.storagesDetails = null
        }
    }

    private fun setProductListFields(
        response: ProductListResponse,
        jsonObject: JsonObject,
        context: JsonDeserializationContext?
    ) {
        response.timeStamp = jsonObject.get("timestamp").toString()
        response.statusCode = jsonObject.get("status_code").toString().toInt()
        response.messageType = jsonObject.get("message_type").toString()
        response.messageBody = jsonObject.get("message_body").toString()

        val storagesDetails = jsonObject.get("products")
        when {

            storagesDetails.isJsonArray -> response.productDetails = context?.deserialize(
                storagesDetails,
                object : TypeToken<List<ProductDetails>>() {}.type
            ) ?: emptyList()

            storagesDetails.isJsonObject -> {
                val singleProductDetails = context?.deserialize<ProductDetails>(storagesDetails, ProductDetails::class.java)
                response.productDetails = if (singleProductDetails != null) {
                    listOf(singleProductDetails)
                }
                else {
                    emptyList()
                }
            }
            else -> response.productDetails = null
        }
    }
}