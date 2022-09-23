import os._
import java.nio.ByteBuffer

/*
[dBASE Table File Format (DBF)](https://www.loc.gov/preservation/digital/formats/fdd/fdd000325.shtml)

  -   0x02 FoxBASE
  -   0x03 FoxBASE+/Dbase III plus, no memo
  -   0x30 Visual FoxPro
  -   0x31 Visual FoxPro, autoincrement enabled
  -   0x32 Visual FoxPro with field type Varchar or Varbinary
  -   0x43 dBASE IV SQL table files, no memo
  -   0x63 dBASE IV SQL system files, no memo

  -   0x83 FoxBASE+/dBASE III PLUS, with memo
  -   0x8B dBASE IV with memo
  -   0xCB dBASE IV SQL table files, with memo
  -   0xF5 FoxPro 2.x (or earlier) with memo
  -   0xE5 HiPer-Six format with SMT memo file
  -   0xFB FoxBASE

 */

case class DBFVariant(val id: Int, val description: String) {
  override def toString = f"$description ($id%#02x)"
}

object DBFUnknown extends DBFVariant(-1, "<???>") {
  override def toString = "<???>"
}

object DBaseIII extends DBFVariant(0x03, "dBase III")
object DBaseIV extends DBFVariant(0x04, "dBase IV")

object DBFVariant {

  def apply(id: Int): DBFVariant = id & 0x7 match {
    case 0x03 => DBaseIII
    case 0x04 => DBaseIV
    case _    => DBFUnknown
  }
}

object Main extends App {

  val bytes = os.read.bytes(os.pwd / "PROFT.dbf")

  val variant = DBFVariant(bytes(0) + 32)

  val y = 1900 + bytes(1).toInt
  val m = bytes(2)
  val d = bytes(3)
  val lastUpdated = f"$y%4d-$m%02d-$d%02d"
  val nRecords = ByteBuffer.wrap(bytes.slice(4, 8).reverse).getInt
  val headerSize = ByteBuffer.wrap(bytes.slice(8, 10).reverse).getShort
  val recordSize = ByteBuffer.wrap(bytes.slice(10, 12).reverse).getShort
  println(variant)
  println(f"Úlima actualización: $lastUpdated")
  println(f"Número de registros: $nRecords")
  println(f"Tamaño cabecera: $headerSize bytes")
  println(f"Tamaño registro: $recordSize bytes")

}
