package stockInfo

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.hadoop.fs._
import org.apache.spark.SparkConf
import com.typesafe.config.ConfigFactory
object topNStocks
{
 def main(args :Array[String])
 {
   val topN = args(0).toInt
      val prop = ConfigFactory.load()
     val conf = new SparkConf().setAppName("Word Count").setMaster(prop.getConfig(args(3)).getString("executionmode"))
   val sc = new SparkContext(conf)
        if(args.length<2)
    {
      println("Usage: ScalaWordCount <input> <output>")
       System.exit(1)
   }
      
     val stocks = sc.textFile(args(1))
     val stockMap = stocks.map(rec => (rec.split(",")(1).toInt ,rec)) 
     
     //Below code for getting data on month basis.
     
     //val NyseMap = nyse.map(rec => (rec.split(",")(1).substring(0,6),rec))
     val stockMapGBK =stockMap.groupByKey()
     
     //Single line to get required output
     
     // val stockByVol = stockMapGBK.flatMap(_._2.toList.sortBy(k => -k.split(",")(6).toInt).distinct.slice(0, topN))
     
     //val nyseByMonth = NyseGBK.flatMap(_._2.toList.sortBy(k => -k.split(",")(6).toInt).distinct.slice(0,3))
     
      stockMapGBK.flatMap(rec => getTopNStocks(rec,topN)).map(rec => rec.split(",")(0)+"\t"+rec.split(",")(1)+"\t"+rec.split(",")(6)).saveAsTextFile(args(2))
    
      
      // nyseByMonth.map(rec => rec.split(",")(1).substring(4,6)+"\t"+rec.split(",")(1).substring(0,4)+"\t"+ rec.split(",")(0)+"\t"+rec.split(",")(6)).saveAsTextFile("E:/logs/outNyse")
     //stockByVol.map(rec => rec.split(",")(0)+"\t"+rec.split(",")(1)+"\t"+rec.split(",")(6)).saveAsTextFile(args(3))
     
     sc.stop()
 }
 def getTopNStocks(rec: (Int,Iterable[String]),topN :Int): Iterable[String] = {
    val StockList = rec._2.toList
    val topNPrices = StockList.
      map (x => x.split(",")(6).toInt).
      sortBy(x => -x).distinct.slice(0, topN)
      
   val topNPriceProducts = StockList.sortBy(x => x.split(",")(6).toFloat).
       filter(x => topNPrices.contains(x.split(",")(6).toFloat))
       
    topNPriceProducts
    
  }
}