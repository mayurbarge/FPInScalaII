import scala.concurrent._


val fut1 = Future {
  Thread.sleep(1000)
}(scala.concurrent.ExecutionContext.global)