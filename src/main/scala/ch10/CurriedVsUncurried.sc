object CurryInference {

  trait MyBox[A] {
    def reduceLeft(f: (A, A) => A): A
    def reduceLeftTotal(initial: A, f: (A, A) => A): A
    def generalReduceLeftTotal[B](initial: B, f: (B, A) => A): B
    /*
      foldLeft is partial function, where type information for type B is given when B is applied. So next time when second param f is applied it knows the type of B already
      This inference allows folds to have same type as well
     */
    def foldLeft[B](initial: B)(f: (B, A) => A): B


    //It so happens that in Scala, type inference proceeds one parameter list at a time. Say you have the following method:
    def foldLeft1[A, B](as: List[A], b0: B, op: (B, A) => B) = ???
    //    Then you’d like to call it in the following way, but will find that it doesn’t compile:
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    //def notPossible = foldLeft1(numbers, 0, _ + _)
    //you will have to call it like one of the below ways:
    def firstWay = foldLeft1[Int, Int](numbers, 0, _ + _)
    def secondWay = foldLeft1(numbers, 0, (a: Int, b: Int) => a + b)
    //This definition doesn’t need any type hints and can infer all of its type parameters.

  }

  val x =
  for {
    1 <- List()
    List() <- List()
  } yield {
    throw new Exception
  }

}