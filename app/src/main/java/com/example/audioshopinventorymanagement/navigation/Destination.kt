package com.example.audioshopinventorymanagement.navigation

sealed class Destination(protected val route: String) {

    val fullRoute: String = route

    companion object {
        private const val LOGINSCREEN_ROUTE = "loginscreen"
        private const val STARTSCREEN_ROUTE = "startscreen"
        private const val PRODUCTSLISTSCREEN_ROUTE = "productlistscreen"
        private const val MODIFYITEMSCREEN_ROUTE = "modifyitemscreen"
        private const val NEWITEMSCREEN_ROUTE = "newitemscreen"
        private const val STORAGESSCREEN_ROUTE = "storagesscreen"
        private const val PRODUCTSOVERVIEWSCREEN_ROUTE = "productsoverviewscreen"
        private const val WAREHOUSESSCREEN_ROUTE = "warehousesscreen"
    }

    object ModifyItemScreenArguments {
        const val barcode = "-1"
    }

    object StoragesScreenArguments {
        const val warehouseId = "-1"
    }

    object ProductsOverviewScreenArguments {
        const val storageId = "-1"
    }

    object LoginScreen : Destination(LOGINSCREEN_ROUTE)
    object StartScreen : Destination(STARTSCREEN_ROUTE)
    object ProductListScreen : Destination(PRODUCTSLISTSCREEN_ROUTE)
    object NewItemScreen : Destination(NEWITEMSCREEN_ROUTE)
    object WareHousesScreen : Destination(WAREHOUSESSCREEN_ROUTE)

    object ModifyItemScreen : Destination("$MODIFYITEMSCREEN_ROUTE/{${ModifyItemScreenArguments.barcode}}") {
        fun passParameters(barcode: String): String {
            return this.route
                .replace(oldValue = "{${ModifyItemScreenArguments.barcode}}", newValue = barcode)
        }
    }

    object StoragesScreen : Destination("$STORAGESSCREEN_ROUTE/{${StoragesScreenArguments.warehouseId}}") {
        fun passParameters(warehouseId: String): String {
            return this.route
                .replace(oldValue = "{${StoragesScreenArguments.warehouseId}}", newValue = warehouseId)
        }
    }

    object ProductsOverviewScreen : Destination("$PRODUCTSOVERVIEWSCREEN_ROUTE/{${ProductsOverviewScreenArguments.storageId}}") {
        fun passParameters(storageId: String): String {
            return this.route
                .replace(oldValue = "{${ProductsOverviewScreenArguments.storageId}}", newValue = storageId)
        }
    }
}