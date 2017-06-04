package com.underscore.chapter1

/**
  * Created by rafa on 03/06/2017.
  */
trait Printable[A] {
  def format(value: A): String
}

final case class Cat(
                      name: String,
                      age: Int,
                      color: String
                    )

object PrintableInstances {
  implicit val stringPrintable = new Printable[String] {
    override def format(value: String): String =
      value.toString
  }
  implicit val intPrintable = new Printable[Int] {
    override def format(value: Int): String =
      value.toString
  }

  implicit val catPrintable = new Printable[Cat] {
    override def format(value: Cat): String =
      s"${value.name} is a ${value.age} year-old ${value.color} cat."
  }
}

object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String =
    printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit =
    println(format(value))

}

object PrintableSyntax {
  implicit class PrintOps[A](value: A) {
    def format(implicit printable: Printable[A]): String =
      printable.format(value)

    def print(implicit printable: Printable[A]): Unit =
      println(format)

  }
}

object Runner extends App {

  import PrintableInstances._
  import PrintableSyntax._

  val cat = Cat("Amy", 1, "blue")
//  Printable.print(cat)
  cat.print
}