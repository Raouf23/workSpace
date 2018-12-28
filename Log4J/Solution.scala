
import com.codes.Errors.ErrCode
import org.apache.log4j.Level

object Solution extends App {

  val value = BigInt("99999999999")
  val maxValue = BigInt("9999999999")

  lazy val log = org.apache.log4j.LogManager.getLogger(CLASSNAME.getClass.getName)
  log.setLevel(Level.INFO)

  log.info( /* Error */ )

}