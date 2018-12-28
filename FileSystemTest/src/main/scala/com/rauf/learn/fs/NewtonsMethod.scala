package com.rauf.learn.fs

object NewtonsMethod {
	def main(args:Array[String]){
		driver
	}

	def driver{

		val fx =(x:Double) => 3*x + math.sin(x) -math.pow(math.E, x)
				val fxprime =(x:Double) => 3 + math.cos(x) -math.pow(Math.E, x)

				val initialGuess = 0.0
				val tolerance = 0.00005

				val answer = newtonsMethod(fx,fxprime,initialGuess,tolerance)
				println(answer)
	}

	def newtonsMethod(fx:Double => Double,fxprime:Double=>Double,x:Double,tolerance:Double):Double={
			var x1 = x
					var xNext = newtonHelperMethod(fx,fxprime,x1)
					while (math.abs(xNext - x1) > tolerance)
					{
						x1 = xNext
								println(xNext) // debugging (intermediate values)
								xNext = newtonHelperMethod(fx, fxprime, x1)
					}
			xNext
	}

	def newtonHelperMethod(fx:Double=> Double,fxprime:Double=>Double,x:Double):Double={
			x-fx(x)/fxprime(x)
	}
}