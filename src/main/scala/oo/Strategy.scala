package oo

/**
  * Created by inakov on 02.11.16.
  */
object Strategy extends App{

  case class Person(firstName: Option[String], middleName: Option[String], lastName: Option[String])

  def isFirstNameValid(person: Person) = person.firstName.isDefined

  def isFullNameValid(person: Person) = person.firstName.isDefined &&
    person.middleName.isDefined && person.lastName.isDefined

  def personCollector(isValid:(Person) => Boolean) = {
    var validatedPeople = List.empty[Person]
    (person: Person) => {
      if(isValid(person)) validatedPeople = person :: validatedPeople
      validatedPeople
    }
  }

  val firstNameValidator = personCollector(isFirstNameValid)
  val fullNameValidator = personCollector(isFullNameValid)

  val p1 = Person(Some("Gosho"), Some("Goshkov"), Some("Goshkov"))
  val p2 = Person(Some("Toshko"), None, None)
  val p3 = Person(None, None, None)

  println(firstNameValidator(p1))
  println(firstNameValidator(p2))
  println(firstNameValidator(p3))

  println(fullNameValidator(p1))
  println(fullNameValidator(p2))
  println(fullNameValidator(p3))

}
