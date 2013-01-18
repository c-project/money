/**
 *
 */
package models

import java.util.ArrayList
import scala.collection._
import java.util._
import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Expense(
    @Required var date:Date, 
    @Required var amount: Int, 
    @Required var summary: String, 
    var tags: Option[String], 
    var category: Option[String]
    ) extends ActiveRecord {
  
} 

object Expense extends ActiveRecordCompanion[Expense] {
  
}