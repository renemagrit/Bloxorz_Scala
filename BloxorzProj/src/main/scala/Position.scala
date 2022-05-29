class Position(crdX: Int, crdY:Int) {
  // Class defines the position with two coordinates
  var x = crdX;
  var y = crdY;

  def moveX(distance: Int):Int ={
    x = x + distance
  }
  def moveY(distance: Int):Int ={
    y = y + distance
  }

}
