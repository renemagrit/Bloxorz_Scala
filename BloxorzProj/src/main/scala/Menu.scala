
import java.nio.file.Files
import java.nio.file.Paths

import scala.io.Source
import scala.io.StdIn.readLine

class Menu {

  val myMaps = new Maps()

  //@tailrec
  def showMenu():Unit={
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
      case 1 => readMapFromFile
      case _ => println("Ponovite unos")
    }

    showMenu
  }


  def readMapFromFile()={
    println("Unesite ime fajla: ")
    val fileName = readLine.toString

    // Path is alerady defined with the folder maps
    val filePath = "D:\\Marta SI\\Master\\FP\\Bloxorz_Scala\\BloxorzProj\\src\\main\\maps\\"+fileName+".txt"

    //Check if file exists
    if (!Files.exists(Paths.get(filePath))){
        println("Uneto ime fajla ne postoji!")

    }else {
      //Reade file and load into strList variable
      var strList = ""
      for (line <- Source.fromFile(filePath).getLines) strList = strList + line.mkString + "\n"

      //Add loaded map to the list of available maps
      myMaps.addMap(fileName, strList.toString())
      myMaps.listOfAvailMaps()
    }
  }
}
