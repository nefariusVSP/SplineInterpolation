object StringTo {
  def ArrayDouble(str: String): Array[Double] = {
    str.replace(" ","");
    val count = str.count(char=> char == ',') + 1
    val mas = new Array[Double](count)
    var i = 0;
    var value : String = str
    while (i < count-1) {
      value.substring(0,value.indexOf(','))
      value = value.substring(value.indexOf(',')+1)
      mas(i) = value.toDouble
      i+=1
    }
    mas(i) = value.toDouble
    mas
  }
}

var s = "213,21.0,-514"
var d = StringTo.ArrayDouble(s)




