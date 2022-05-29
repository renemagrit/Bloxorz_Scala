class Maps {

  //List of available maps with their names
  var availMaps: Map[String, String] = Map.empty[String, String]

  // Add new map to a list of available maps
  def addMap(name: String, mapValue: String) ={
    availMaps = availMaps ++ Map(name -> mapValue)
  }

  //Get the map with defined name, and return Matrix of fields
  def convMapToList(name: String):List[List[Char]] = {
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
}
