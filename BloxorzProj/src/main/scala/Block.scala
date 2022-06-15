class Block(initP1: Position, initP2: Position){

    /*
    * Initiali Block is placed on same field
    * When you roll the block, than Block will take 2 fields maximally
    * p1 - the first block field
    * p2 - the second block field
    * */

    var p1 = initP1
    var p2 = initP2


    def isVertical: Boolean = (p1.x == p2.x) && (p1.y == p2.y)

    def isHorizonal: Boolean = (p1.y == p2.y)

    def changeCordX(x1: Int, x2: Int) = new Block(new Position(p1.x + x1, p1.y), new Position(p2.x+x2, p2.y))

    def changeCordY(y1: Int, y2: Int) = new Block(new Position(p1.x, p1.y + y1), new Position(p2.x, p2.y+y2))

    /*** ROLLING ***/
    def rollLeft():Block ={
      if(isVertical) changeCordX(-2, -1)
      else if (isHorizonal) changeCordX(-1, -2)
      else changeCordX(-1, -1)
    }
    def rollRigth():Block ={
      if(isVertical) changeCordX(1, 2)
      else if (isHorizonal) changeCordX(2, 1)
      else changeCordX(1, 1)
    }
    def rollUp():Block ={
      if(isVertical) changeCordY(-2, -1)
      else if (isHorizonal) changeCordY(-1, -1)
      else changeCordY(-1, -2)
    }
    def rollDown():Block ={
      if(isVertical) changeCordY(1, 2)
      else if (isHorizonal) changeCordY(1, 1)
      else changeCordY(2, 1)
    }


}
