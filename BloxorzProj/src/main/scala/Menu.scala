import scala.io.StdIn.readLine

class Menu {


  def showMenu()={
    println("- - - - - - - -  MENU - - - - - - - - ")
    println("Izaberite jednu od ponudjenih opcija:")
    println("1. Učitavanje mape terena iz fajla")
    println("2. Započni igru")
    println("3. Odigraj potez [d - dole, g - gore, l - levo, d - desno]")
    println("4. Odigraj sekvencu poteza iz fajla")
    println("Unos: ")

    val command = readLine()
    println("Izabrai ste komandu " + command)
  }
}
