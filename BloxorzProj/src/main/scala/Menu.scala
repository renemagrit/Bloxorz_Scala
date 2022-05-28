import scala.io.Source
import scala.io.StdIn.readLine

class Menu {


  def showMenu()={
    println("- - - - - - - -  MENU - - - - - - - - ")
    println("Izaberite jednu od ponudjenih opcija:")
    println("1. Učitavanje mape terena iz fajla")
    println("2. Započni igru")
    println("3. Odigraj potez [d - dole, g - gore, l - levo, d - desno]")
    println("4. Odigraj sekvencu poteza iz fajla")
    println("Unos: ")

    val command = readLine.toInt

    println("Izabrai ste komandu " + command)

    command match {
      case 1 => loadMapFromFile()
      case _ => println("Ponovite unos")
    }
  }

  def loadMapFromFile()={
    val filename = "D:\\Marta SI\\Master\\FP\\Bloxorz_Scala\\BloxorzProj\\src\\main\\maps\\map1.txt"
    for (line <- Source.fromFile(filename).getLines) {
      println(line)
    }
  }
}
