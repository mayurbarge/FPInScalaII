package visualguide


class VisualGuide {

}

object VisualGuide extends App {

  val fruits = List("Oranges","Apples")
  val fruitLenth = fruits.aggregate(0)((acc, elem) => {
    acc + elem.length
  }, (fruit1, fruit2)=>fruit1+fruit2)

  println(fruitLenth)
}
