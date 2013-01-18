package controllers

import play.api.mvc._
import models._
import views.html.expense._
import play.api.data._
import play.api.data.Forms._
import views.html.defaultpages.badRequest
import models.Expense
import com.github.aselab.activerecord.dsl._
import com.github.aselab.activerecord._
import org.omg.CosNaming.NamingContextPackage.NotFound

object ExpenseController extends Controller {

  val expenseForm = Form(
      mapping (
        "date" -> date,
        "amount" -> number,
        "summary" -> text,
        "tags" -> optional(text),
        "category" -> optional(text)
      )(Expense.apply)(Expense.unapply)
  )

  def add = Action { implicit request =>
    expenseForm.bindFromRequest.fold(
        hasErrors => BadRequest(views.html.expense.create(hasErrors)),
        success => {
          println(expenseForm.bindFromRequest.get)
          expenseForm.bindFromRequest.get.create()
          Redirect(routes.ExpenseController.list())
        }
    )
  }
  
  def list = Action {
    Ok(views.html.expense.list(Expense.all))
  }
  
  def show(id: Long) = Action {
    val expense: Option[Expense] = Expense.find(id)
    if (expense.isEmpty) {
      NotFound
    } else {
	  Ok(views.html.expense.show(id, expenseForm.fill(expense.get)))
    }
  }
  
  def delete(id: Long) = Action {
    Expense(id) match {
      case Some(expense) => 
        transaction {expense.delete()}
        Redirect(routes.ExpenseController.list())
      case _ => NotFound
    }
  }
  
  def edit(id: Long) = Action { implicit request =>
    Expense(id) match {
      case Some(expense) =>
        expenseForm.bindFromRequest.fold(
          hasErrors => {
            BadRequest(views.html.expense.show(id, hasErrors))
          },
          success => {
            println(expenseForm.bindFromRequest.data)
            val updated:Expense = Expense.bind(expenseForm.bindFromRequest.data)(expense)
            println(updated.id)
            updated.save()
            Redirect(routes.ExpenseController.list)
//            Ok(views.html.expense.show(id, expenseForm.fill(updated)))
          }
        )
      case _ => NotFound
  }
  }
  
  def create = Action {
    Ok(views.html.expense.create(expenseForm))
  }
}