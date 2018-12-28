package com.rauf.learn.fs
import resource._
import java.io._
import com.typesafe.config.ConfigFactory

object fsTest {
	def main(args:Array[String]){

		//val filePath = """E:\logs\words.txt"""
		//val canonicalFilename = """E:\logs\fsTest.txt"""

		val appConfig = ConfigFactory.load()

				val filePath = appConfig.getString("Config.general.inputPath")

				val canonicalFilename = appConfig.getString("Config.general.canonicalFilename")  
val fileMap = Map[Int,String]()
				try{
					for (source <- managed(scala.io.Source.fromFile(filePath))) {
						for (line <- source.getLines) {
							println(line)
						}
					}
				}
		catch{
		case e: FileNotFoundException => println("Couldn't find that file.")
		case e: IOException => println("Got an IOException!")
		}
		val file = new File(canonicalFilename)
				val bw = new BufferedWriter(new FileWriter(file))
				bw.write("Hola! Coms esta... mucha Gracias Muy Bein")
				bw.close()


				val source1 = scala.io.Source.fromFile(filePath)
				for(char <-source1){
					print(char.toUpper)
				}
		source1.close()

	}

}