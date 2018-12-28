package com.rauf.learn.fs

import com.typesafe.config.ConfigFactory
import org.joda.time._
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import org.joda.time.format.PeriodFormatterBuilder
import java.util.Calendar
import java.io.File
import scala.util._

object DataValidation {
  def main(args: Array[String]) {

    val appConfig = ConfigFactory.load()

    val INCOMING_FOLDER = appConfig.getString("Config.general.incomingFolder")
    val x = appConfig.entrySet().iterator()
    //				while(x.hasNext())
    //				{
    //				  println(x.next())
    //				}
    //val INCOMING_FOLDER = args(0).toString()

    val pinned1 = appConfig.getString("Config.general.pinning1Folder")
    val pinned2 = appConfig.getString("Config.general.pinning2Folder")

    val IdentityImplThis = scala.collection.mutable.Map[String, Any]()
    val IdentityImplThat = scala.collection.mutable.Map[String, Any]()

    def mapAssignment(l1: Seq[String], M1: scala.collection.mutable.Map[String, Any], seqValue: Int) = {
      val values = l1(seqValue).split(",")
      M1 += ("idType" -> values(0))
      M1 += ("idNumber" -> values(1))
     
    }

    val content1 = scala.io.Source.fromFile(pinned1).getLines().sliding(2, 2)
    for (line <- content1) {
      //  println(line)
      if (line.size > 1) {
        mapAssignment(line, IdentityImplThis, 0)
        mapAssignment(line, IdentityImplThat, 1)
        println(StringComparison.comparisonMethod2(IdentityImplThis("idNumber").toString(), IdentityImplThat("idNumber").toString()))
      }
    }

   println(IdentityImplThis.size)
    for ((k, v) <- IdentityImplThis) println(s"key: $k, value: $v")

    println(IdentityImplThat.size)
    for ((k, v) <- IdentityImplThat) println(s"key: $k, value: $v")

    val data = scala.io.Source.fromFile(INCOMING_FOLDER)
    val fileName = new File(INCOMING_FOLDER)
    println(fileName.getName.contains(".txt"))
    //val originFormat = new SimpleDateFormat("yyyyMMdd")

    val format = new SimpleDateFormat("yyyyMMddHHmm")
    val suffix = format.format(Calendar.getInstance().getTime())

    println(suffix)

    val sysDateTime = new DateTime()
    val fmt = DateTimeFormat.forPattern("yyyyMMdd")

    val NewFmt = DateTimeFormat.forPattern("yyyyMM")

    val fmt1 = DateTimeFormat.forPattern("yyyyMMdd")

    val fmt2: DateTimeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmss")

    def parseDate2(format: DateTimeFormatter, InputDate: String) = {
      format.parseDateTime(InputDate)
    }

  /*  println("-----------------------------New Test Date" + parseDate2(NewFmt, "201409"))
    val EndDate = parseDate2(NewFmt, "201602").dayOfMonth().getMaximumValue
    println("ENDDATE -----------" + EndDate)
    // println("--------------------------" + parseDate2(fmt2, "20180223235959"))

    println("--------------------------" + parseDate2(fmt2, "20170104202544"))*/

    
    val headerDate = "20180228"
    
    println("###########" + parseDate2(fmt1,headerDate).plusMonths(1))
    
    
    val proDate = new DateTime(1522906811L * 1000)
    println("-----------------------Proper Date :-" + proDate)
    println("++++++++++++++++++" + proDate.getYear + proDate.getMonthOfYear + proDate.getDayOfMonth)

    val dateTime = "11/15/2013 08:00:00"
    val dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")
    println("------------>" + dtf.withZoneUTC())

    def parseDate(Input: String): Try[DateTime] = {
      Try(fmt.parseDateTime(Input))
    }

    println("------" + parseDate("20001212"))

    for (line <- data.getLines()) {
      val ID_TYPE = line.substring(0, 1)
      val ID_NUMBER = line.substring(2, 12)
      val ACC_NUMBER = line.substring(13, 30)
      val ID_NAME = line.substring(31, 74)
      val Acc_opening_date = line.substring(76, 84)
      val Expiry_date = line.substring(84, 92)
      val private_brand = line.substring(149, 178)
      println(ID_TYPE)

      if (ID_TYPE.toInt != 1 && ID_TYPE != 2) {
        println("Error Code 1001 has been occured")

      }

      println(isNumeric(ID_NUMBER))
      println(ID_NUMBER + " \t" + (ID_NUMBER.length()))

      println(ID_NUMBER.toInt)
      if ((ID_NUMBER.toInt > 1 && ID_NUMBER.toInt < 99999999) || (ID_NUMBER.toInt > 1000000000 && ID_NUMBER.toLong < 7999999999L)) {
        println("ID_NUMBER is ok")
      } else println("ERROR 0201")

      println(idValidation(ID_TYPE.toInt, ID_NUMBER.toLong))

      println(isNumeric(ACC_NUMBER))
      println(ACC_NUMBER + "  " + (ACC_NUMBER.length() + 1))
      println(ID_NAME + "  " + (ID_NAME.length() + 1))
      println(ID_NAME.trim() + "  " + (ID_NAME.trim().length() + 1))
      println(Acc_opening_date)
      println(Expiry_date)

      println("openingdate--> " + fmt.parseDateTime(Acc_opening_date))
      println("expDate --> " + fmt.parseDateTime(Expiry_date))

      println(parseDate(Expiry_date).getOrElse(0))

      val open = fmt.parseDateTime(Acc_opening_date)
      val exp = fmt.parseDateTime(Expiry_date)
      val currentDate = sysDateTime
      //val dateZero = fmt.parseDateTime("00000000")
      println(open.plusMonths(36))
      println(open.getDayOfMonth)
      println(open.getMonthOfYear)
      println(open.getYear)

      val date = 
      println(dateValidation(open))
      //println(dateValidation(dateZero))
      println("joda Time-: " + sysDateTime)
      println("jdk Time-: " + sysDateTime.toDate())

      if (open.isBefore(sysDateTime)) {
        println("Error code 1502")
      }

      if (sysDateTime.isBefore(open)) {
        println("Error code 1502")
      } else { println("Error1501") }

      if (open.isAfter(sysDateTime)) {
        println("Error code 1502")
      }

      val ldays = Days.daysBetween(open, exp)
      println(ldays.getDays)

      val lperiod = new Period(open, exp)
      println(lperiod.getWeeks)

      if (private_brand.trim() != "") {
        println(private_brand)
      } else println("Blank code")
    }
  }

  def preValidation(Id_type: Int, Id_num: String) = Id_type match {
    case 2 =>
      if ((Id_num.length == 10) && (Id_num.charAt(0) == '8' || Id_num.charAt(0) == '9')) {
        Id_num.substring(1, Id_num.length).toLong
      } else if (Id_num.length == 11 && Id_num.charAt(0) == '1') {
        Id_num.substring(0, Id_num.length - 1).toLong
      }
    case 3 =>
      if ((Id_num.length == 10) && (Id_num(0) == '7' || Id_num(0) == '6')) { Id_num.substring(0, Id_num.length() - 1) }
  }

  def idValidation(Id: Int, Id_Number: Long): Boolean = Id match {
    case 1 => ((Id_Number > 1L && Id_Number < 99999999L) || (Id_Number > 1000000000L && Id_Number < 7999999999L))
    case 2 => (Id_Number > 800000000L && Id_Number < 999999999L)
    case 3 => (Id_Number > 600000000L && Id_Number < 799999999L)
    case 4 => (Id_Number > 1L && Id_Number < 99999999L)
    case _ => false
  }

  def leftZeroTrim(s: String) = s.replaceAll("^0+", "")

  def isNumeric(str: String) = scala.util.Try(str.toDouble).isSuccess

  def dateValidation(date: DateTime): Boolean = {
    try {
      val dd: Int = date.getDayOfMonth
      val mm: Int = date.getMonthOfYear
      val yy: Int = date.getYear

      if (dd != 0 && mm != 0 && yy != 0) {
        if (dd == 31 && (mm == 4 || mm == 6 || mm == 9 || mm == 11)) {
          false
        } else if (mm == 2) {
          if (yy % 4 == 0) {
            if (dd == 30 || dd == 31) {
              false
            } else {
              true
            }
          } else {
            if (dd == 29 || dd == 30 || dd == 31) {
              false
            } else {
              true
            }
          }
        } else {
          true
        }
      } else {
        false
      }
    } catch {
      case e: IllegalFieldValueException =>
        println(e)
        true
    }
  }
}