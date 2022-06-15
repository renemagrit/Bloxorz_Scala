
import java.nio.file.Files
import java.nio.file.Paths

import scala.io.Source
import scala.io.StdIn.readLine

class Menu {

  val myMaps = new Maps()
  var myGame = new Game()

  //@tailrec
  def showMenu():Unit={
    println("- - - - - - - -  MENU - - - - - - - - ")
    println("Izaberite jednu od ponudjenih opcija:")
    println("1. Učitavanje mape terena iz fajla")
    println("2. Započni igru")
    println("3. Odigraj potez [d - dole, g - gore, l - levo, d - desno]")
    println("4. Odigraj sekvencu poteza iz fajla")
    println("5. Kreiranje novih mapa")
    println("Unos: ")

    val command = readLine.toInt

    println("Izabrai ste komandu " + command)

    command match {
      case 1 => readMapFromFile
      case 2 => startGame
      case 3 => manualPlayGame
      case 4 => filePlayGame
      case 5 => updateMaps
      case 6 => solverPlayGame

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

    }
  }

  def startGame() ={
    if(myMaps.isListOfAvailableMapsEmpty) println("Lista dostupnh mapa je prazna. Molimo unesite mapu!")
    else {
      println("Unesite ime mape: ")
      myMaps.listOfAvailableMapNames()
      val mapName = readLine.toString
      myGame.myMap = myMaps
      myGame.myMapName = mapName
      myGame.gameCall
    }
  }
  def manualPlayGame() ={
    println("Unesite potez: ")
    val potez = readLine.toString
    if(!myGame.manualPlayGame(potez))
      println("Igra je završena. Započnite novu igru!")

  }
  def filePlayGame()={
    println("Unesite ime fajla: ")
    val fileName = readLine.toString

    // Path is alerady defined with the folder maps
    val filePath = "D:\\Marta SI\\Master\\FP\\Bloxorz_Scala\\BloxorzProj\\src\\main\\moves\\"+fileName+".txt"

    //Check if file exists
    if (!Files.exists(Paths.get(filePath))){
      println("Uneto ime fajla ne postoji!")

    }else {
      //Reade file and load into strList variable
      var strList = ""
      for (line <- Source.fromFile(filePath).getLines if (!myGame.manualPlayGame(line))) println("Igra je gotova!")
    }
  }
  def updateMaps(): Unit ={
    println("Unesite ime fajla: ")
    val mapName = readLine.toString
    println("Unesite x kordinaru: ")
    val x = readLine.toInt
    println("Unesite y kordinaru: ")
    val y = readLine.toInt
    if(!myMaps.isPositionValidOnMap(mapName, new Position(x, y))) return
    myMaps.replaceCornerBlock(mapName, x, y)
  }

  def solverPlayGame()={
    myGame.solverPlayGame
  }
}
