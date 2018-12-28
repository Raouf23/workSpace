package sparkSql

import com.typesafe.config.ConfigFactory

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.hadoop.fs._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object sst {
  def main(args: Array[String]) {
    val appConf = ConfigFactory.load()
    val conf = new SparkConf().
      setAppName("Total Revenue - Daily - Data Frames").
      setMaster(appConf.getConfig(args(2)).getString("deploymentMaster"))

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    sqlContext.setConf("spark.sql.shuffle.partitions", "2")
    
    import sqlContext.implicits._

    val inputPath = args(0)
    val outputPath = args(1)

    val fs = FileSystem.get(sc.hadoopConfiguration)
    val inputPathExists = fs.exists(new Path(inputPath))
    val outputPathExists = fs.exists(new Path(outputPath))

    if (!inputPathExists) {
      println("Input Path does not exists")
      return
    }

    if (outputPathExists) {
      fs.delete(new Path(outputPath), true)
    }

    val ordersDF = sc.textFile(inputPath + "/orders").
      map(rec => {
        val a = rec.split(",")
        Orders(a(0).toInt, a(1).toString(), a(2).toInt, a(3).toString())
      }).toDF()

    ordersDF.registerTempTable("orders")

    val orderItemsDF = sc.textFile(inputPath + "/order_items").
      map(rec => {
        val a = rec.split(",")
        OrderItems(
          a(0).toInt,
          a(1).toInt,
          a(2).toInt,
          a(3).toInt,
          a(4).toFloat,
          a(5).toFloat)
      }).toDF()

    orderItemsDF.registerTempTable("order_items")
    
    val totalRevenueDaily = sqlContext.sql("select o.order_date, sum(oi.order_item_subtotal) " +
          "from orders o join order_items oi " +
          "on o.order_id = oi.order_item_order_id " +
          "where o.order_status = 'COMPLETE' " +
          "group by o.order_date " +
          "order by o.order_date")

    totalRevenueDaily.rdd.saveAsTextFile(outputPath)

  }
}