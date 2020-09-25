trait Animal
 class Cat() extends Animal {
  def name = "Cat"
}
 class Tiger() extends Cat
{
 override def name = "Tiger"
}

val cats = List(new Cat(),new Cat())
val tiger = List(new Tiger(),new Tiger())

tiger.isInstanceOf[List[Animal]]
tiger.isInstanceOf[List[Animal]]
//cats.fold[Tiger](new Tiger())((t1,t2)=>t1)
//tiger.fold[Cat](new Tiger())((t1,t2)=>t1)

