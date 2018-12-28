package byCategory

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import com.typesafe.config._
import org.apache.spark.SparkConf

object StockTopN
{
   def main(args:Array[String])
  {
    
    val conf = new SparkConf().setAppName("TopStocks").setMaster("local")
    val sc = new SparkContext(conf)
    
       if(args.length<2)
    {
       println("Usage: ScalaWordCount <input> <output>")
       System.exit(1)
    }
    val topN = args(0).toInt
    val products = sc.textFile(args(1))
    
   val productFilter = products.filter(rec => rec.split(",")(0).toInt != 685)
   
   val productMap = productFilter.map(rec => (rec.split(",")(1).toInt,rec))
   
   val productGBK = productMap.groupByKey()
   
   productGBK.flatMap(rec => getTopNproducts(rec,topN)).saveAsTextFile(args(2))

  }
  
  def getTopNproducts(rec: (Int,Iterable[String]),topN :Int): Iterable[String] = {
    val productList = rec._2.toList
    val topNPrices = productList.
      map (x => x.split(",")(4).toFloat).
      sortBy(x => -x).distinct.slice(0, topN)
      
   val topNPriceProducts = productList.sortBy(x => x.split(",")(4).toFloat).
       filter(x => topNPrices.contains(x.split(",")(4).toFloat))
       
    topNPriceProducts
    
  }
}