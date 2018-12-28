package com.raouf.productmock



class InventoryManager(inentoryController:InventoryController) {

	def manageInventory(num:Int, isSold:Boolean)={
			if(isSold) inentoryController.addInventory(num) else inentoryController.subInventory(num)
	}

}