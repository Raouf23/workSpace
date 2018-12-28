package sqlTest

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import com.typesafe.config.ConfigFactory

import org.apache.hadoop.fs._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.SparkConf

object TotalRevenueDailySQL
{
   def main(args:Array[String])
   {
      val appConf = ConfigFactory.load()
      val conf = new SparkConf().setAppName("Total Revenue-daily-SparkSQl").setMaster(appConf.getConfig(args(2)).getString("executionmode"))
    
      val sc = new SparkContext(conf)
      val sqlContext = new SQLContext(sc)
      sqlContext.setConf("spark.sql.shuffle.partitions", "2")
      
      import sqlContext.implicits._
      
      val inpath = args(0)
      val outpath = args(1)
      
      val fs = FileSystem.get(sc.hadoopConfiguration)
      val inpathExists = fs.exists(new Path(inpath))
      val outpathExists = fs.exists(new Path(outpath))
      
      if (!inpathExists)
      {
        println("Input path doesn't exists")
        return
      }
      
      if(outpathExists)
      {
        fs.delete(new Path(outpath), true)
      }
      
      val ordersDF = sc.textFile(inpath+"/orders").
      map(rec =>{
        val a = rec.split(",")
        Orders(a(0).toInt,a(1).toString(),a(2).toInt,a(3).toString())
      }).toDF()
      
      ordersDF.registerTempTable("orders")
     //ordersDF.rdd.take(5).foreach(println)
      val OrderItemsDF = sc.textFile(inpath+"/order_items").
      map(rec => {
        val a =rec.split(",")
        OrderItems(a(0).toInt,a(1).toInt,a(2).toInt,a(3).toInt,a(4).toFloat,a(5).toFloat)
      }).toDF()
      
    OrderItemsDF.registerTempTable("order_items")
    //OrderItemsDF.rdd.take(5).foreach(println)
     val categories = sc.textFile(args(0)+"/categories").
     map(rec => {
       val a =rec.split(",")
       Categories(a(0).toInt,a(1).toInt,a(2))
     }).toDF()
     
     categories.registerTempTable("categories")
     //categories.rdd.take(5).foreach(println)
    val departments = sc.textFile(args(0)+"/departments").
    map(rec => {
      val a = rec.split(",")
      Departments(a(0).toInt, a(1))
    }).toDF()
    
    departments.registerTempTable("departments")
        //departments.rdd.take(5).foreach(println)
    
    val products = sc.textFile(args(0)+"/products").
    map(rec =>{
      val a = rec.split(",")
      Products(a(0).toInt, a(1).toInt, a(2).toString(), a(3).toString(), a(5).toString)
    }).toDF()
    
     products.registerTempTable("products")
    //products.rdd.take(5).foreach(println)
     
//     val departCategories = sqlContext.sql("select d.department_id,d.department_name,c.category_id "+
//                            "from departments d JOIN categories c " +
//                            "on  d.department_id = c.category_department_id")
//                            
//       departCategories.rdd.saveAsTextFile(outpath)
       
    
     
     val rpdSql = sqlContext.sql("select d.department_name, o.order_date ,sum(oi.order_item_subtotal) total_quantity from departments d join categories c on d.department_id = c.category_department_id join products p on c.category_id = p.product_category_id join order_items oi on p.product_id = oi.order_item_product_id join orders o on oi.order_item_order_id = o.order_id WHERE o.order_status in ('COMPLETE' ,'CLOSED') group by d.department_name,o.order_date order by o.order_date desc")
//    
//    val totalRevenueDaily = sqlContext.sql("select o.order_date, sum(oi.order_item_subtotal) " +
//         "from orders o join order_items oi " +
//         "on o.order_id = oi.order_item_order_id " +
//         "where o.order_status = 'COMPLETE' " +
//          "group by o.order_date " +
//          "order by o.order_date")
//    
//    
//  totalRevenueDaily.rdd.saveAsTextFile(outpath)
 
    rpdSql.rdd.saveAsTextFile(outpath)
    sc.stop

    
   }
}