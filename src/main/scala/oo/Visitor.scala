package oo

/**
  * Created by inakov on 02.11.16.
  */

//Source: http://lampwww.epfl.ch/~odersky/papers/ScalableComponent.pdf
object Visitor extends App{

  trait PerimeterShapes {
    trait Shape {
      def perimeter: Double
    }

    class Circle(radius: Double) extends Shape{
      override def perimeter: Double = 2 * Math.PI * radius
    }

    class Rectangle(width: Double, height: Double) extends Shape {
      override def perimeter: Double = 2 * width + 2 * height
    }
  }

  trait MorePerimeterShapes extends PerimeterShapes{
    class Square(side: Double) extends Shape{
      override def perimeter: Double = 4 * side
    }
  }

  trait AreaShapes extends PerimeterShapes{
    trait Shape extends super.Shape{
      def area: Double
    }

    class Circle(radius: Double) extends super.Circle(radius) with Shape{
      override def area: Double = Math.PI * Math.pow(radius, 2.0)
    }

    class Rectangle(width: Double, height: Double) extends super.Rectangle(width, height) with Shape{
      override def area: Double = width * height
    }
  }

  object FirstShapes extends PerimeterShapes{
    val someCircle = new Circle(1)
    val someRectangle = new Rectangle(4, 6)
  }

  object ExtendedShapes extends AreaShapes{
    val someShape = List[Shape](new Circle(4), new Rectangle(2,2))
  }

  import FirstShapes._

  println(someCircle.perimeter)
  println(someRectangle.perimeter)


  import ExtendedShapes._

  someShape.foreach(shape => println(shape.perimeter))
  someShape.foreach(shape => println(shape.area))
}
