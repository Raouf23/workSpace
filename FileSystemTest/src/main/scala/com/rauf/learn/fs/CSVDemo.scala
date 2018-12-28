package com.rauf.learn.fs

import java.io.File

object CSVDemo extends App {

	println("Month, Income, Expenses, Profit")

	val dirCheck = """E:\logs"""
	val sFile = """E:\logs\finance.txt"""
	val sData = scala.io.Source.fromFile(sFile)
	for(line <- sData.getLines())
	{
		val cols = line.split(",").map(_.trim())

				println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}|")
	}

	def getListOfDirectory(dir1:String):List[String]={
			val d1 = new File(dir1)
					d1.listFiles().filter(_.isDirectory).map(_.getName).toList
	}

	val dirList = getListOfDirectory(dirCheck)
			dirList.foreach(println)


	def getListOfFiles(dir:String):List[File]= {
					val d = new File(dir)
							if(d.exists && d.isDirectory)
							{
								d.listFiles.filter(_.isFile()).toList
							}
							else{
								List[File]()
							}
	}

	val fileList = getListOfFiles(dirCheck)
			fileList foreach(println)
}