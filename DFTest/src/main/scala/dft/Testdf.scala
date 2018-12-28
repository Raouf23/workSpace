package dft

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import com.typesafe.config.ConfigFactory

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.hadoop.fs._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object Testdf  {
  
  case class Marks(name:String,math:Int,physics:Int,chemistry:Int)
  
 def main(args:Array[String])={
   
   val appConf = ConfigFactory.load()
    val conf = new SparkConf().setAppName("Spark-Sql-TESt").setMaster("local")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    sqlContext.setConf("spark.sql.shuffle.partitions", "2")
    
    import sqlContext.implicits._
    
    val marks = sc.textFile("E:/logs/marks.txt").map(_.split(",")).map(rec => Marks(rec(0),rec(1).trim.toInt,rec(2).trim.toInt,rec(3).trim.toInt)).toDF()
             
             
   marks.show()
   
   val markSheetTemp = marks.withColumn("Total",$"math"+$"physics"+$"chemistry")
   val markSheet = markSheetTemp.withColumn("Percentage", $"Total"/90*100)
   
   
   markSheet.sort("Total").show()
   sc.stop()
 }
  
}