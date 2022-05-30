class Game(map: Maps, mapName: String) {

  val myMap = new Maps()
  val myMapName = mapName

  val startPos = new Position(2,1)

  var myBlock = new Block(startPos, startPos)

  def isPositionValidOnMap(pos: Position, map: List[List[Char]]):Boolean = {

    if ((pos.x < 0) || (pos.y < 0)) false
    else if (pos.x >= map(pos.x).length) false
    else if (pos.y >= map.length) false
    else if (map(pos.y)(pos.x) == '-') false
    else true
  }

  def checkBlock(myBlock: Block, map: List[List[Char]]):Boolean = {
    isPositionValidOnMap(myBlock.p1, map) && isPositionValidOnMap(myBlock.p2, map)
  }
  def printGame(block: Block)={
    var mapList = map.convMapToList(myMapName)
    for((line,j) <- mapList.zipWithIndex){
      for((e, i) <- line.zipWithIndex){
        if((block.p1.x == i &&  block.p1.y == j) || (block.p2.x == i && block.p2.y == j)) print("$")
        else print(e)
      }
      println()
    }
    println()
  }
  def gameCall(): Unit ={
    println("GAME CALL")

    println(checkBlock(myBlock, map.convMapToList(myMapName)))
    println(checkBlock(myBlock.rollLeft(), map.convMapToList(myMapName)))
    println(checkBlock(myBlock.rollRigth(), map.convMapToList(myMapName)))
    println(checkBlock(myBlock.rollUp(), map.convMapToList(myMapName)))
    println(checkBlock(myBlock.rollDown(), map.convMapToList(myMapName)))

    printGame(myBlock)
    printGame(myBlock.rollDown())
    myBlock = myBlock.rollDown()
    printGame(myBlock.rollRigth())
    myBlock = myBlock.rollRigth()
    printGame(myBlock.rollUp())
  }

}
