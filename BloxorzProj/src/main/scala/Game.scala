class Game() {

  var myMap = new Maps()
  var myMapName = ""

  var startPos = new Position(-1,-1)
  var stopPos = new Position(-1,-1)

  var myBlock = new Block(startPos, startPos)
  var gameFinish = true
  /** Block Helper Functions*/
  //1. REQ: Valid block position on the MAP without special block sign
  def isPositionValidOnMap(pos: Position, map: List[List[Char]]):Boolean = {
    println("/////////")
    println(pos.x+" "+pos.y +" "+ map(0).length +" " + map.length)

    if (pos.x < 0)  false
    else if(pos.y < 0) false
    else if (pos.x > map(0).length) false
    else if (pos.y > map.length) false
    else if (map(pos.y)(pos.x) == '–') false
    else if (map(pos.y)(pos.x) == '.') false
    else true
  }

  //3. REQ: Check if two positions are equal
  def isEqualPos (p1:Position, p2:Position):Boolean=(p1.x == p2.x) && (p1.y == p2.y)

  //4. RQE: Check if the block position on the map is valid
  def checkBlock(myBlock: Block, map: List[List[Char]]):Boolean = {
    val s1 = isPositionValidOnMap(myBlock.p1, map)
    val s2 = isPositionValidOnMap(myBlock.p2, map)
    (s1 && s2)
  }


  /** Game helper functions*/
  //1. REQ: Check if it's the end of game
  def isEnd(myBlock:Block, stopPos: Position):Boolean = isEqualPos(myBlock.p1 , stopPos) && isEqualPos(myBlock.p2,stopPos)

  //2. REQ: Print Game sequence
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
  /*****************************************************************************************************/

  //1. REQ: Initiate new game
  def gameCall={
    println("* * * GAME STARTED * * *")
    startPos = myMap.findCharacterPosition('S', myMap.convMapToList(myMapName))
    stopPos = myMap.findCharacterPosition('T', myMap.convMapToList(myMapName))
    myBlock = new Block(startPos, startPos)
    gameFinish = false

    printGame(myBlock)
  }

  /** Manual play Game*/
  def manualPlayGame(command: String): Boolean ={

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

    }else{
      gameFinish = true;
      println("Game Over :(")

    }
    true

  }
  /** UTIL SOLVER Functions*/
  //5. REQ: All valid

  def validBlockNeighbours( myBlock: Block, map: List[List[Char]]) :  List[(Block, Move)]={
    val myAllNeighborBlocks: List[(Block, Move)]  = List((myBlock.rollUp(), MoveUp),
        (myBlock.rollDown(), MoveDown),
        (myBlock.rollRigth,MoveRight),
        (myBlock.rollLeft(), MoveLeft))
    for(e <- myAllNeighborBlocks if checkBlock(e._1, map)) yield (e)
  }

  def neighborsWithHistory(NeighborBlocks: List[(Block, Move)], history: List[Move]): List[(Block, List[Move])] = {
    val a = NeighborBlocks
    val h = history
    for (n <- NeighborBlocks) yield (n._1, n._2 :: history)
  }


  def myContain(blck: Block, explored: Set[Block]): Boolean ={
    for(e <- explored ) if (isEqualPos(e.p2, blck.p2) && isEqualPos(e.p1, blck.p1)) return true
    false
  }

  def newNeighborsOnly(neighbors: List[(Block, List[Move])], explored: Set[Block]): List[(Block, List[Move])] = {
    val a = neighbors
    val e = explored
    for ((b, m) <- neighbors if !myContain(b, explored)) yield (b, m)
    //for ((b, m) <- neighbors if !explored.contains(b)) yield (b, m)
  }

  def from(initial: List[(Block, List[Move])], explored: Set[Block]): List[(Block, List[Move])] =
    if (initial.isEmpty) List.empty
    else {
      val s = initial.head._1
      val neighbors = neighborsWithHistory(validBlockNeighbours(initial.head._1,myMap.convMapToList(myMapName)), initial.head._2)
      val neighborsNew = newNeighborsOnly(neighbors, explored)
      val expl = explored ++ (neighborsNew map(_._1))
      initial.head ::  from(initial.tail ++ neighborsNew, expl)
      //initial
    }

  //lazy val pathsFromStart: List[(Block, List[Move])] = from(List((myBlock, List())), Set(myBlock))
  /**Solver play Game*/
  def solverPlayGame()={
    println(startPos.x + " "+ startPos.y)
    println(stopPos.x + " "+ stopPos.y)
    println("-------------")
    val a = from(List((myBlock, List())), Set(myBlock))
    //for((e, m) <- a )println(e.p1.x + "," + e.p1.y +" "+e.p2.x + "," + e.p2.y )
  }
}
