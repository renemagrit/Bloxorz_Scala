
/*
  Position is defined with two coordinates:
  (x, y) - same as standard coordinate system
  ---------- X
  |
  |   $
  |
  |
  Y
*/


class Position(crdX: Int, crdY:Int) {
  // Class defines the position with two coordinates
  var x : Int = crdX;
  var y : Int = crdY;

  def moveX(distance: Int) ={
    x = x + distance
    x.toInt
  }

  def moveY(distance: Int) ={
    (y = y + distance)
    y.toInt
  }

}
