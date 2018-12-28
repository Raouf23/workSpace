package test
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object test1
  {
    def main(args : Array[String])
    {
      val conf = new SparkConf().setAppName("Joins").setMaster("local")
      
      val sc = new SparkContext(conf);
    val i = List(1, 2, 3, 4, 5)
    val dataRDD = sc.parallelize(i)
    dataRDD.saveAsTextFile("E:/logs/outtest")
  }
}