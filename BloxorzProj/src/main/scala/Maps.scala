
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

}
