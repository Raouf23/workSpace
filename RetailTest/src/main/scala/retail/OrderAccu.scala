package retail
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import com.typesafe.config._
import org.apache.hadoop.fs._

object OrderAccu
{
   def main(args : Array[String])
    {
   
      val prop = ConfigFactory.load()
     val conf = new SparkConf().setAppName("Accumulator-Test").setMaster(prop.getConfig(args(2)).getString("executionmode"))
   val sc = new SparkContext(conf)
        if(args.length<2)
    {
      println("Usage: ScalaWordCount <input> <output>")
       System.exit(1)
   }
   
    val fs = FileSystem.get(sc.hadoopConfiguration)
    
    val inpath = new Path(args(0))
    val isInpathExists = fs.exists(inpath)
    if (!isInpathExists)
    {
      println("invalid input path")
      return
    }
    val outpath = new Path(args(1))
     val isOutpathExists = fs.exists(outpath)
    if (isOutpathExists)
    {
      println("output path is already exist... ")
      return
    }
    
    val orders = sc.textFile(args(0) +"/orders")
    val order_items = sc.textFile(args(0)+"/order_items")
//    val categories = sc.textFile(args(0)+"/categories")
//    val departments = sc.textFile(args(0)+"/departments")
//    val products = sc.textFile(args(0)+"/products")
//    
    val ordersCompletedAccum = sc.accumulator(0, "ordersCompleted count")
    val ordersFilterInvokedAccum = sc.accumulator(0, "orders filter invoked count")
    
     val ordersCompleted = orders.
      filter(rec => {
        ordersFilterInvokedAccum += 1
        if (rec.split(",")(3) == "COMPLETE") {
          ordersCompletedAccum += 1
        }
        rec.split(",")(3) == "COMPLETE"
      })
      
     val ordersAccum = sc.accumulator(0, "orders count")
     
    val ordersMap = ordersCompleted.
      map(rec => {
        ordersAccum += 1
        (rec.split(",")(0).toInt, rec.split(",")(1))
      })
    
    val orderItemAccum = sc.accumulator(0, "Order_Items_Accumulator")
    val orderItemsMap = order_items.
    map(rec => {
        orderItemAccum += 1
        (rec.split(",")(1).toInt,rec.split(",")(4).toFloat)
    })
    
    val orderItemsValuesAccum = sc.accumulator(0, "reduceByKey values count")
    val orderItems = orderItemsMap.
      reduceByKey((acc, value) => {
        orderItemsValuesAccum += 1
        acc + value
      })

    val ordersJoin = ordersMap.join(orderItems).sortByKey()
      ordersJoin.saveAsTextFile(args(1))
    
    }  
}