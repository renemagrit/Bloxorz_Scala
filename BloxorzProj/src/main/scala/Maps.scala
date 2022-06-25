import scala.io.Source

class Maps {

  //List of available maps with their names
  var availMaps: Map[String, String] = Map.empty[String, String]

  // Add new map to a list of available maps
  def addMap(name: String, mapValue: String) ={
    availMaps = availMaps ++ Map(name -> mapValue)
  }
  def getMapByName(name: String): String = availMaps.get(name).get

  //Get the map with defined name, and return Matrix of fields
  def convMapToList(name: String): List[List[Char]] = List(getMapByName(name).toString().split("\n").map(str => List(str: _*)): _*)
  def convStringMapToList(mapValue: String): List[List[Char]] = List(mapValue.toString().split("\n").map(str => List(str: _*)): _*)

  //Print all maps with their names
  def listOfAvailMaps()={
    println("--- Available Maps ---")
    for(e<- availMaps) {
      println(e)
    }
  }

  def listOfAvailableMapNames()= for((e, i) <- availMaps.keys.zipWithIndex) println((i + 1) + ". " + e)

  def isListOfAvailableMapsEmpty():Boolean = availMaps.isEmpty

  def findCharacterPosition(sign: Char, map: List[List[Char]]): Position ={
    var pos = new Position(-1, -1)
    for((e, y) <- map.zipWithIndex){
      for((k, x) <- map(y).zipWithIndex if map(y)(x) == sign) pos = new Position(x, y)
    }
    pos
  }


  //************* HELPER FUNCTIONS *******************************************

  def isPositionValidOnMap(mapValue: String ,pos: Position):Boolean = {
    val map = convStringMapToList(mapValue)
    if ((pos.x < 0) || (pos.y < 0)) false
    else if (pos.x >= map(pos.x).length) false
    else if (pos.y >= map.length) false
    else true
  }

  def replaceSignInMap(mapValue: String,sign:Char, x: Int, y:Int): String={
    val newMap = List(mapValue.toString().split("\n").map(str => List(str: _*)): _*)
    val map = for((e,i) <- newMap.zipWithIndex) yield
      if(i == y) e.patch(x, Seq(sign), 1).mkString.appended('\n') else e.mkString.appended('\n')
    map.flatten.toList.mkString
  }
  //0.REQ: Update Map with the latest map value
  def mapValueUpdate(mapName: String, mapValue:String) = {
    availMaps = availMaps + (mapName -> mapValue)
  }

  //1.REQ: Remove corner block
  def replaceCornerBlock(mapValue: String, x: Int, y: Int):String = replaceSignInMap(mapValue, '-', x, y)

  //2.REQ: Add corner block
  def addCornerBlock(mapValue: String, x: Int, y: Int):String = replaceSignInMap(mapValue, 'o', x, y)

  //3.REQ: Add special block
  def addSpecialBlock(mapValue: String, x: Int, y: Int):String = replaceSignInMap(mapValue, '.', x, y)

  //4.REQ: Remove special block
  def removeSpecialBlock(mapValue: String, x: Int, y: Int):String = replaceSignInMap(mapValue, 'o', x, y)

  //5.REQ: Start position change
  def updateStartBlock(mapValue: String, x:Int, y: Int):String = {
    val startPos = findCharacterPosition('S', convStringMapToList(mapValue))
    val temp = replaceSignInMap(mapValue, 'o', startPos.x, startPos.y)
    replaceSignInMap(temp, 'S', x, y)
  }

  //6.REQ: End position change
  def updateEndBlock(mapValue: String, x:Int, y: Int):String = {
    val stopPos = findCharacterPosition('T', convStringMapToList(mapValue))
    val temp = replaceSignInMap(mapValue, 'o', stopPos.x, stopPos.y)
    replaceSignInMap(temp, 'T', x, y)
  }
}
