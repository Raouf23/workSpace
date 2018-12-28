package sqlTest

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.hadoop.fs._
import org.apache.spark.SparkConf
import com.typesafe.config.ConfigFactory
import org.apache.spark.SparkConf

object UPElection 
{
  def main(args:Array[String])
  {
    val prop = ConfigFactory.load()
    val conf = new SparkConf().setAppName("UP-ELECTION").setMaster("local")
//    setMaster(prop.getConfig(args(2)).getString("executionmode"))
    val sc = new SparkContext(conf)
    
//        val inpath = args(0)
//      val outpath = args(1)
//    
//     val fs = FileSystem.get(sc.hadoopConfiguration)
//      val inpathExists = fs.exists(new Path(inpath))
//      val outpathExists = fs.exists(new Path(outpath))
//      
//      if (!inpathExists)
//      {
//        println("Input path doesn't exists")
//        return
//      }
//      
//      if(outpathExists)
//      {
//        fs.delete(new Path(outpath), true)
//      }
      
      val withheaderElections = sc.textFile("E:/logs/ls2014.tsv")
      val header = withheaderElections.first
       //header: String = state  constituency    candidate_name  sex     age     category        partyname   partysymbole
      //general posatal total   pct_of_total_votes      pct_of_polled_votes     totalvoters
     
      val withOutHeaderElection = withheaderElections.filter(rec => rec !=header)
      
      val UPElection = withOutHeaderElection.filter(rec => rec.split("\t")(0).equals("Uttar Pradesh"))
      
      val UPElectionMap = UPElection.map(rec => (rec.split("\t")(1).toString,rec))
      
      val UPElectionMapConGrpRes = UPElectionMap.groupByKey().flatMap(_._2.toList.sortBy(k => -k.split("\t")(10).toInt).take(1))
      
      val seatWonByParty = UPElectionMapConGrpRes.map(rec =>(rec.split("\t")(6).toString,rec))
      
      seatWonByParty.countByKey().foreach(println)
      
      
      sc.stop()
  }
}