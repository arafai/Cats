package com.underscore.chapter1

import cats.Show

;

/**
  * Created by rafa on 03/06/2017.
  */

object Show1 {

  import cats.Show
  import cats.instances.int._
  import cats.instances.string._

  val intShow: Show[Int] = Show.apply[Int]
  val stringShow: Show[String] = Show.apply[String]

  intShow.show(1)
  stringShow.show("asd")
}

object Show2 extends App {

  import cats.syntax.show._
  import cats.instances.int._
  import cats.instances.string._

  1.show
  "asd".show
  final class B()

  implicit val bShow: Show[B] = Show.apply[B](b => b.getClass.getSimpleName)

  println((new B).show)

}