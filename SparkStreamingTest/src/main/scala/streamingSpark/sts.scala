package streamingSpark

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.storage.StorageLevel

object sts
{
 def main(args:Array[String])
 {
   val conf = new SparkConf().setAppName("Streaming Test").setMaster("local[4]")
   val ssc = new StreamingContext(conf,Seconds(20))
   
   val lines = ssc.socketTextStream("localhost",9999)
   
   val words = lines.flatMap(_.split(" "))
   val pairs = words.map(word =>(word,1))
   val wordCounts = pairs.reduceByKey(_ + _)
   
   wordCounts.print()
   
   ssc.start()
    ssc.awaitTermination()
 }
}