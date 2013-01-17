/**
 *
 */
package models

import java.util.ArrayList
import scala.collection._
import java.util.Date


case class ExpenseItem(var id: Int, var date:Date, var amount: Int, var summary: String, var tags: Option[String], var category: Option[String]) {
  def apply(id: Int, dateAsString:String, amount: Int, summary: String, tags: Option[String], category: Option[String]) = {
    this.id = id
    this.amount = amount
    this.summary = summary
    this.tags = tags
    this.category = category
    val format = new java.text.SimpleDateFormat("dd.MM.yyyy")
    this.date = format.parse(dateAsString)
  }
}



object ExpenseItem {
  
  var items: mutable.Map[Int, ExpenseItem] = mutable.Map()
  var seq: Int = 0
	
  def all(): Iterable[ExpenseItem] = items.values
  
  def create(expense: ExpenseItem): Int = {
    expense.id = seq
    items += (seq -> (expense))
    seq += 1
    seq
  }
  
  def edit(expense: ExpenseItem) = {
    items.getOrElseUpdate(expense.id, expense)
  }

  def get(id: Int): ExpenseItem = {
    items.get(id).get
  }
  
  def delete(itemToDelete: Int) {
    items -= itemToDelete
  }
  
}