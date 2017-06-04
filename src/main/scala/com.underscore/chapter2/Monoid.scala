package com.underscore.chapter2

import cats.Monoid

/**
  * Created by rafa on 03/06/2017.
  */
package object interfaces {
  trait MonoidT[A] {
    def combine(x: A, y: A): A

    def empty: A
  }
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) =
      monoid
  }
  object MonoidInstances {
    implicit val orMonoid = new Monoid[Boolean] {

      override def empty: Boolean = false

      override def combine(x: Boolean, y: Boolean): Boolean = x || y
    }
  }

  //  implicit val andMonoid = new Monoid[Boolean] {
  //
  //    override def empty: Boolean = true
  //
  //    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  //  }

  implicit def setMonoid[A] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty

    override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y
  }

}


object M extends App {

  import cats.Monoid
  import cats.Semigroup
  import cats.instances.string._
  import cats.instances.int._
  import cats.instances.option._

  val res = Monoid[String].combine("asd", "fgh")
  println(res)
  val res2 = Semigroup[String].combine("Hi ", "there")
  println(res2)

  val a = Option(22)
  val b = Option(22)

  val res3 = Semigroup.apply[Option[Int]].combine(a,b)
  println(res3)

  import cats.syntax.semigroup._
  val stringResult = "Hi" |+| "there" |+| Monoid[String].empty
}

object Added extends App {
  import cats.instances.int._
  import cats.instances.option._

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    items.foldLeft(monoid.empty)(monoid.combine)
  }

  final case class Order(totalCost: Double, quantity: Double)
  implicit val orderMonoid = new Monoid[Order] {
    override def empty: Order = Order(0d, 0d)

    override def combine(x: Order, y: Order): Order =
      Order(
        x.totalCost + y.totalCost,
        x.quantity + y.quantity
      )
  }

  println(add(List(Order(1,2), Order(1,2))))
}