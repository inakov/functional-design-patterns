package oo

/**
  * Created by inakov on 02.11.16.
  */
object TemplateMethod extends App{

  def makeGradeReport(
    numToLetter: (Double) => String,
    printGradeReport: (Seq[String]) => Unit) = (grades: Seq[Double]) =>{
      printGradeReport(grades.map(numToLetter))
    }

  def fullGradeConverter(grade: Double): String = {
    if(grade <= 5.0 && grade > 4.0) "A"
    else if(grade <= 4.0 && grade > 3.0) "B"
    else if(grade <= 3.0 && grade > 2.0) "C"
    else if(grade <= 2.0 && grade > 0.0) "D"
    else if(grade == 0.0) "F"
    else "N/A"
  }

  def printHistogram(grades: Seq[String]) = {
    val counts = grades.groupBy(identity).map{case (k, v) => (k, v.size)}.toSeq.sorted
    for(count <- counts) {
      val stars= "*" * count._2
      println("%s: %s".format(count._1,stars))
    }
  }

  val fullGradeReport = makeGradeReport(fullGradeConverter, printHistogram)

  val sampleGrades = Vector(5.0,5.0,5.0,4.0,4.4,2.2,3.3,3.5)
  fullGradeReport(sampleGrades)

}
