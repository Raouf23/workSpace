package test

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object WordCount {
  def main(args: Array[String]): Unit = {
    val inpath = "E:/logs/wordcount.txt"
    val outpath = "E:/SparkOutput"
    val sparkConf = new SparkConf().setAppName("WordCountTest").setMaster("local")
   val  sc = new SparkContext(sparkConf)
    
    val output = get(inpath,sc)
    output.take(5).foreach(println)
    sc.stop()
  }
  
  def get(url:String, sc:SparkContext):RDD[(String,Int)]= {
    val lines = sc.textFile(url)
   lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _) 
  }
}