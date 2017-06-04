package com.underscore.chapter1

/**
  * Created by rafa on 03/06/2017.
  */
object Eq1 extends App {

  import cats.Eq
  import cats.instances.int._

  val eqInt: Eq[Int] = Eq.apply[Int]
  eqInt.eqv(1, 2)

  import cats.syntax.eq._

  123 === 122

  import cats.instances.int._
  import cats.instances.string._
  import cats.instances.option._

  Option(1) === None
  (Some(1): Option[Int]) === (None: Option[Int])

  import java.util.Date
  import cats.instances.long._

  implicit val dateEqual = Eq.instance[Date] { (date1, date2) =>
    date1.getTime === date2.getTime
  }

  final case class Cat(name: String, age: Int, color: String)
  implicit val catEqual = Eq.instance[Cat] { (c1, c2) =>
    c1.name === c2.name && c1.age === c2.age && c1.color === c2.color
  }

  val cat1 = Cat("Garfield", 35, "orange and black")
  val cat2 = Cat("Heathcliff", 30, "orange and black")
  println(cat1 === cat2)
  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(optionCat1 === optionCat2)

}
