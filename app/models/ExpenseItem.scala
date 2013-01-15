/**
 *
 */
package models

import java.util.ArrayList


case class ExpenseItem(var id: Int, var amount: Int, var summary: String, tags: Option[String], category: Option[String])



object ExpenseItem {
  
  var items: Map[Int, ExpenseItem] = Map()
  var seq: Int = 0
	
  def all(): Iterable[ExpenseItem] = items.values
  
  def create(expense: ExpenseItem): Int = {
    expense.id = seq
    items += (seq -> (expense))
    seq = seq + 1
    seq
  }
//  def create(expense: ExpenseItem): Int = {
//    items + (seq -> (expenseItem.id = seq))
//    seq + 1
//  }
  
  def get(id: Int): ExpenseItem = {
    items.get(id).get
  }
  
  def delete(itemToDelete: Int) {
    items = items-itemToDelete
  }
  
}