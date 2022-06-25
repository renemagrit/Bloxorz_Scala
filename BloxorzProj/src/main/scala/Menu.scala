
import java.nio.file.Files
import java.nio.file.Paths
import scala.io.Source
import scala.io.StdIn.readLine
import scala.util.matching.Regex

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


  def updateMapFncs(mapValue:String, command: Int, x: Int, y:Int):String = {
    if(!myMaps.isPositionValidOnMap(mapValue, new Position(x, y))) return mapValue

    command match {
        case 1 => myMaps.replaceCornerBlock(mapValue, x, y)
        case 2 => myMaps.addCornerBlock(mapValue, x, y)
        case 3 => myMaps.addSpecialBlock(mapValue, x, y)
        case 4 => myMaps.removeSpecialBlock(mapValue, x, y)
        case 5 => myMaps.updateStartBlock(mapValue, x, y)
        case 6 => myMaps.updateEndBlock(mapValue, x, y)
        case 7 => myMaps.mapInversion(mapValue)
        case 8 => myMaps.mapSpecialBlockExchange(mapValue)
        case _ => mapValue                                      //Input error
      }

  }

  def updateMaps(): Unit ={
    println("Unesite ime fajla: ")
    val mapName = readLine.toString

    println("- - - - - - - SUB MENU - - - - - - - ")
    println("Izaberite Opciju:")
    println("1. Ukloni plocu sa ivice terena")
    println("2. Dodaj plocu na ivicu terena")
    println("3. Zamena obicne ploce specijalnom")
    println("4. Zamena specijalne ploce obicnom")
    println("5. Posavljanje nove Startne pozicije")
    println("6. Posavljanje nove Ciljne pozicije")
    println("7. Inverzija Mape")
    println("8. Ssjsjsjsjsj")
    println("Unos: <Opcija>(x,y)")

    val Pattern = """\d\(\d,\d\)""".r
    val lines = Iterator.continually(io.StdIn.readLine).takeWhile(Option(_).fold(false)(_.nonEmpty)).
      toList.filter{ fi => Pattern.findFirstIn(fi).isDefined}

    var myMap = myMaps.getMapByName(mapName)
    for(l <-lines) {
      val h = l.split("()")
      myMap = updateMapFncs(myMap, h(0).toString.toInt, h(2).toString.toInt, h(4).toString.toInt)
    }
    println(myMap)
    myMaps.mapValueUpdate(mapName, myMap)
  }

  def solverPlayGame()={
    myGame.solverPlayGame
  }
}
