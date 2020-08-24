/*
Tail call elimination
Functional programming insists on tail call elimination and scala has a limited support for tail call recursion
Scala does nothing to help with Inter-method tail calls,  tail calls between functions.
Scala has made it a design choice, that Java's call semantics has to be emulated/compatible in Scala.
Java function calls require every function call to have a stack frame.
Functional programming insists on tail calls. Why is it so, because of you may want to compose lots of functions together.
And you want it to be transparent user.

e.g. def foo(v:Int): Int =
        if(p(v)) b(v) else c(v) + 1
        After invocation of b scala function returns

def composeA11[A](fs: List[A => A]): A => A =
    fs.foldLeft(a => a)(_ andThen _)
When you compose a list of functions like this you end up in stackOverflow
 */

def composeA11[A](fs: List[A => A]): A => A = {
  fs.foldLeft((a:A) => a)(_ andThen _)
}
/*
Recursion is like
TailRec->resume => TailRec->resume => TailRec
Instead of getting a value from function call, you put value inside the Suspension and return that
 */
sealed trait TailRec[A]
case class Return[A](a:A) extends TailRec[A]
case class Suspend[A](resume: () => TailRec[A]) extends TailRec[A]

/*def composeA[A](fs: List[A => A]): A => A =
  (a1:A) => (fs.foldLeft(Suspend(_)) { (f,r) =>
    (a2:A) =>
      Suspend(
        () => {
          val z: A = r(f(a2))
          z
        }

      )
    })(a1).run*/


