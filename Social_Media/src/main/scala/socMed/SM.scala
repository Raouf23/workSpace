package socMed

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import com.typesafe.config.ConfigFactory

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.hadoop.fs._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object SM 
{
   def main(args:Array[String])={
   
   val appConf = ConfigFactory.load()
    val conf = new SparkConf().setAppName("Spark-Sql-TESt").setMaster("local")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    sqlContext.setConf("spark.sql.shuffle.partitions", "2")
    
    import sqlContext.implicits._
    
    val answerRDD12 = sqlContext.read.format("com.databricks.spark.csv").option("inferSchema","true").option("header","true").load("E:/answers/answers.csv")
    
    val epoch2date:(Column)=>Column = (x) =>{from_unixtime(x)}
    
    var ans_df = answerRDD12.withColumn("Question_time",epoch2date(col("qt")))
    
    ans_df = ans_df.withColumn("Answer_time",epoch2date(col("at")))
    
    val ansWithResTime = ans_df.withColumn("Response_time", $"at" - $"qt")
      ansWithResTime.persist()
      
     val totalQues = ansWithResTime.count()
     
     println(totalQues)
     
    val sum_responseTime = ansWithResTime.agg(sum("Response_time")).map { row => row.getInt(0) }
    
    println(sum_responseTime)
    
    val ansWith1h =ansWithResTime.filter($"Response_time" < 3600)
    
    val quesWith1h = ansWith1h.select("qid").distinct().count()
    val tagWith1h = ansWith1h.select("tags").distinct().count()
    
    println("Number of questions which got answered within 1 hour: " + quesWith1h)
    println("Tags of questions which got answered within 1 hour: " + tagWith1h)
    
    sc.stop()
   }
}