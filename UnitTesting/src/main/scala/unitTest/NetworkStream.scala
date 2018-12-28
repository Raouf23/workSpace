package unitTest

import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.storage.StorageLevel

object NetworkStream
{
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Streaming").setMaster("local[4]")
    val ssc = new StreamingContext(sparkConf,Seconds(10))
    
    
    val lines = ssc.socketTextStream("10.129.155.129", 9999, StorageLevel.MEMORY_AND_DISK_SER_2)
    lines.flatMap(x =>x.split(" ")).map(x => (x,1)).reduceByKey(_ + _).print()
    ssc.start()
    ssc.awaitTermination()
    
  }
}