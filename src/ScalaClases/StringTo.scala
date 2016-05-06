package ScalaClases

/**
  * Created by Администратор on 06.05.2016.
  */
object StringTo {
  def ArrayDouble(str: String): Array[Double] = {
    var value = str.replace(" ","");
    val count = str.count(char=> char == ',') + 1
    val mas = new Array[Double](count)
    var i = 0;
    while (i < count-1) {
      mas(i) = value.substring(0,value.indexOf(',')).toDouble
      value = value.substring(value.indexOf(',')+1)
      i+=1
    }
    mas(i) = value.toDouble
    //scala.util.Sorting.quickSort(mas)
    mas
  }
}
