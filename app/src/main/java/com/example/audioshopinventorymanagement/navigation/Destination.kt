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

//    object ModifyItemSreenArguments {
//        const val firstParameter = "firstParameter"
//        const val secondParameter = "secondParameter"
//    }

    object LoginScreen : Destination(LOGINSCREEN_ROUTE)
    object StartScreen : Destination(STARTSCREEN_ROUTE)
    object ProductListScreen : Destination(PRODUCTSLISTSCREEN_ROUTE)
    object ModifyItemScreen : Destination(MODIFYITEMSCREEN_ROUTE)
    object NewItemScreen : Destination(NEWITEMSCREEN_ROUTE)
    object CategoriesScreen : Destination(CATEGORIESSCREEN_ROUTE)
    object OneCategoryScreen : Destination(ONECATEGORYSCREEN_ROUTE)
    object WareHousesScreen : Destination(WAREHOUSESSCREEN_ROUTE)

//    object ThirdScreen : Destination("$THIRD_ROUTE/{$firstParameter}/{$secondParameter}") {
//        fun passParameters(firstParameter: String, secondParameter: String): String {
//            return this.route
//                .replace(oldValue = "{${Arguments.firstParameter}}", newValue = firstParameter)
//                .replace(oldValue = "{${Arguments.secondParameter}}", newValue = secondParameter)
//        }
//    }
}