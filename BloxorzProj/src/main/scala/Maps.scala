import scala.io.Source

class Maps {

  //List of available maps with their names
  var availMaps: Map[String, String] = Map.empty[String, String]

  // Add new map to a list of available maps
  def addMap(name: String, mapValue: String) ={
    availMaps = availMaps ++ Map(name -> mapValue)
  }

  //Get the map with defined name, and return Matrix of fields
  def convMapToList(name: String): List[List[Char]] = {
    val mapValue = availMaps.get(name)
    List(mapValue.toString().split("\n").map(str => List(str: _*)): _*)
  }

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

  def isPositionValidOnMap(mapName: String ,pos: Position):Boolean = {
    val map = convMapToList(mapName)
    if ((pos.x < 0) || (pos.y < 0)) false
    else if (pos.x >= map(pos.x).length) false
    else if (pos.y >= map.length) false
    else true
  }

  def replaceSignInMap(mapName: String,sign:Char, x: Int, y:Int): List[String] ={
    val newMap = convMapToList(mapName)
    for((e,i) <- newMap.zipWithIndex) yield
      if(i == y) e.patch(x, Seq(sign), 1).mkString.appended('\n') else e.mkString.appended('\n')
  }
  //0.REQ: Update Map with the latest map value
  def mapValueUpdate(mapName: String, mapValue:String) = {
    availMaps = availMaps + (mapName -> mapValue)
  }


  //1.REQ: Remove corner block
  def replaceCornerBlock(name: String, x: Int, y: Int):String = replaceSignInMap(name, '-', x, y).flatten.toList.mkString

  //2.REQ: Add corner block
  def addCornerBlock(name: String, x: Int, y: Int):String = replaceSignInMap(name, 'o', x, y).flatten.toList.mkString

  //3.REQ: Add special block
}
