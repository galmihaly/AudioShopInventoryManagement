package com.example.audioshopinventorymanagement.navigation

sealed class Destination(protected val route: String, vararg params: String) {

    val fullRoute: String = route

    companion object {
        private const val LOGINSCREEN_ROUTE = "loginscreen"
        private const val STARTSCREEN_ROUTE = "startscreen"
        private const val PRODUCTSLISTSCREEN_ROUTE = "productlistscreen"
        private const val MODIFYITEMSCREEN_ROUTE = "modifyitemscreen"
        private const val NEWITEMSCREEN_ROUTE = "newitemscreen"
        private const val CATEGORIESSCREEN_ROUTE = "categoriesscreen"
        private const val ONECATEGORYSCREEN_ROUTE = "onecategoryscreen"
        private const val WAREHOUSESSCREEN_ROUTE = "warehousesscreen"
    }

    object ModifyItemSreenArguments {
        const val barcode = -1
    }

    object LoginScreen : Destination(LOGINSCREEN_ROUTE)
    object StartScreen : Destination(STARTSCREEN_ROUTE)
    object ProductListScreen : Destination(PRODUCTSLISTSCREEN_ROUTE)
    object NewItemScreen : Destination(NEWITEMSCREEN_ROUTE)
    object CategoriesScreen : Destination(CATEGORIESSCREEN_ROUTE)
    object OneCategoryScreen : Destination(ONECATEGORYSCREEN_ROUTE)
    object WareHousesScreen : Destination(WAREHOUSESSCREEN_ROUTE)

    object ModifyItemScreen : Destination("$MODIFYITEMSCREEN_ROUTE/{${ModifyItemSreenArguments.barcode}}") {
        fun passParameters(barcode: String): String {
            return this.route
                .replace(oldValue = "{${ModifyItemSreenArguments.barcode}}", newValue = barcode)
        }
    }
}