package sqlTest

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import com.typesafe.config.ConfigFactory

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.hadoop.fs._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

//import spark.implicits._

object st
{
  
  case class Person(name: String, age: Long)
  
  def main(args:Array[String])
  {
     val appConf = ConfigFactory.load()
    val conf = new SparkConf().setAppName("Spark-Sql-TESt").setMaster("local")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    sqlContext.setConf("spark.sql.shuffle.partitions", "2")
    
    import sqlContext.implicits._
    
    val path2 = "E:/logs/people.txt"
    val path1 =  "E:/logs/employees.json"
    
    val newPeopleDF = sqlContext.read.json(path1)
    newPeopleDF.printSchema()
//    newPeopleDF.createOrReplaceTempView("Employees")
//    
//    val salary  = sqlContext.sql("SELECT * FROM Employees")
//    salary.show()
//    
//    val peopleDF = sc.textFile(path2).map(_.split(",")).map(attributes => Person(attributes(0),attributes(1).trim.toInt)).toDF
//    
//    peopleDF.createOrReplaceTempView("people")
//    
//    val teenagerDF = sqlContext.sql("SELECT name,age FROM people where age BETWEEN 13 AND 19")
//    
//    teenagerDF.map(teen => "NAME :" + teen(0)).show()
//    
//    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]
//     
//    teenagerDF.map(teenager => teenager.getValuesMap[Any](List("name", "age"))).collect().foreach(println)
       
  }
}