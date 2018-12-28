import com.typesafe.config.ConfigFactory

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.hadoop.fs._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object DFtest {
  
  def main(args: Array[String]) {
    val appConf = ConfigFactory.load()
    val conf = new SparkConf().
      setAppName("Total Revenue - Daily - Data Frames").
      setMaster(appConf.getConfig(args(2)).getString("deploymentMaster"))

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    sqlContext.setConf("spark.sql.shuffle.partitions", "2")
    
    import sqlContext.implicits._

   // val inputPath = args(0)
    //val outputPath = args(1)

    val fs = FileSystem.get(sc.hadoopConfiguration)
   // val inputPathExists = fs.exists(new Path(inputPath))
   // val outputPathExists = fs.exists(new Path(outputPath))

//    if (!inputPathExists) {
//      println("Input Path does not exists")
//      return
//    }

    if (outputPathExists) {
      fs.delete(new Path(outputPath), true)
    }

    
    case class Marks(name:String,math:Int,physics:Int,chemistry:Int)
//    val ordersDF = sc.textFile(inputPath + "/orders").
//      map(rec => {
//        val a = rec.split(",")
//        Orders(a(0).toInt, a(1).toString(), a(2).toInt, a(3).toString())
//      }).toDF()

    println("Hello world")
 // val marks = sc.textFile("E:/logs/marks.txt").map(_.split(",")).map(rec => Marks(rec(0),rec(1).trim.toInt,rec(2).trim.toInt,rec(3).trim.toInt)).toDF()
             
             
  // marks.show()
   
  }
  
}