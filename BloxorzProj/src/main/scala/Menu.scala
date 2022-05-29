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
      case 1 => println(readMapFromFile())
      case _ => println("Ponovite unos")
    }
  }

  def readMapFromFile():List[List[Char]]={
    //TODO: add option to insert path for new file
    val filename = "D:\\Marta SI\\Master\\FP\\Bloxorz_Scala\\BloxorzProj\\src\\main\\maps\\map1.txt"
    var strList = ""
    for (line <- Source.fromFile(filename).getLines) strList = strList + line.mkString + "\n"

    //Split map to matrix of Lits
    List(strList.toString().split("\n").map(str => List(str: _*)): _*)
  }
}
