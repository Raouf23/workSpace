package com.raouf.productmock

trait InventoryController {

	def addInventory(numItmArr:Int):Product

	def subInventory(numItmSold:Int):Product

}