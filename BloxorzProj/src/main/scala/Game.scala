class Game() {

  var myMap = new Maps()
  var myMapName = ""

  var startPos = new Position(-1,-1)
  var stopPos = new Position(-1,-1)

  var myBlock = new Block(startPos, startPos)
  var gameFinish = true

  def isPositionValidOnMap(pos: Position, map: List[List[Char]]):Boolean = {
    if ((pos.x < 0) || (pos.y < 0)) false
    else if (pos.x >= map(pos.x).length) false
    else if (pos.y >= map.length) false
    else if (map(pos.y)(pos.x) == '-') false
    else true
  }
  def isPositionSpecial(myBlock: Block, map: List[List[Char]]): Boolean =
    myBlock.isVertical && (map(myBlock.p1.y)(myBlock.p2.x) == '.')

  def isEqualPos (p1:Position, p2:Position):Boolean=(p1.x == p2.x) && (p1.y == p2.y)

  def isEnd(myBlock:Block, stopPos: Position):Boolean = isEqualPos(myBlock.p1 , stopPos) && isEqualPos(myBlock.p2,stopPos)

  def checkBlock(myBlock: Block, map: List[List[Char]]):Boolean =
    isPositionValidOnMap(myBlock.p1, map) && isPositionValidOnMap(myBlock.p2, map)

  def printGame(block: Block)={
    var mapList = myMap.convMapToList(myMapName)
    for((line,j) <- mapList.zipWithIndex){
      for((e, i) <- line.zipWithIndex){
        if((block.p1.x == i &&  block.p1.y == j) || (block.p2.x == i && block.p2.y == j)) print("$")
        else print(e)
      }
      println()
    }
    println()
  }

  def gameCall={
    println("* * * GAME STARTED * * *")
    startPos = myMap.findCharacterPosition('S', myMap.convMapToList(myMapName))
    stopPos = myMap.findCharacterPosition('T', myMap.convMapToList(myMapName))
    myBlock = new Block(startPos, startPos)
    gameFinish = false

    printGame(myBlock)
  }

  def manualPlay(command: String): Boolean ={

    if(gameFinish) return false

    var tempBLock = myBlock
    command match {
      case "d" => tempBLock = myBlock.rollDown()
      case "u" => tempBLock = myBlock.rollUp()
      case "l" => tempBLock = myBlock.rollLeft()
      case "r" => tempBLock = myBlock.rollRigth()

      case _ => println("Ponovite unos")

    }

    if(checkBlock(tempBLock, myMap.convMapToList(myMapName)))
    {
      myBlock =  tempBLock
      printGame(myBlock)
      if(isEnd(myBlock, stopPos)){
        gameFinish = true;
        println("You win! ")
      }
      if( isPositionSpecial(myBlock, myMap.convMapToList(myMapName))) {
        gameFinish = true;
        println("Game Over :(")
      }

    }else{
      gameFinish = true;
      println("Game Over :(")

    }
    true

  }

}
