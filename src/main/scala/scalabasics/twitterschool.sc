def -()=()
class Dog(name:String)
new Dog("Scoobie") == new Dog("Scoobie")
new Dog("Scoobie") eq new Dog("Scoobie")
new Dog("Scoobie") equals new Dog("Scoobie")

case class Cat(name:String)
Cat("Scoobie") == Cat("Scoobie") // value equality
Cat("Scoobie") eq Cat("Scoobie") // reference equality
Cat("Scoobie") equals Cat("Scoobie")

val x: Long = 987654321
val y: Float = x  // 9.8765434E8 (note that some precision is lost in this case)
val z: Double = 1234.5E18

val face: Char = '☺'
val number: Int = face  // 9786
val tupleInMaps = Map((1,"one"),(2,"two"))
