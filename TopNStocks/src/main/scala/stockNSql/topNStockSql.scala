package stockNSql

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import com.typesafe.config.ConfigFactory
//import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.hadoop.fs._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.SparkConf

object topNStockSql 
{
  def main(args:Array[String]){
    val appConf = ConfigFactory.load()
    val conf = new SparkConf().setAppName("Top-Stocks-SparkSQl").setMaster(appConf.getConfig(args(2)).getString("executionmode"))
   
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
      
      val stocksDF = sc.textFile(inpath).
      map(rec =>{
        val a = rec.split(",")
        stocks(a(0), a(1), a(2).toFloat, a(3).toFloat, a(4).toFloat, a(5).toFloat, a(6).toLong)
      }).toDF()
      
      stocksDF.registerTempTable("stocks_eod")
      
      val topNStocks = sqlContext.sql("""select * from (select trademonth, stockticker, monthly_volume, DENSE_RANK() OVER (PARTITION BY trademonth ORDER BY monthly_volume) as rnk from (select substr(tradedate, 1, 6) trademonth, stockticker, sum(volume) monthly_volume from stocks_eod group by substr(tradedate, 1, 6), stockticker) q) q1 where rnk <=5 order by trademonth, rnk""")
      
   topNStocks.rdd.collect()
    sc.stop                 
  }
}