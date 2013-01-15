package controllers

import play.api.mvc._
import models._
import views.html.expense._
import play.api.data._
import play.api.data.Forms._
import views.html.defaultpages.badRequest

object ExpenseController extends Controller {

  val expenseForm = Form(
      mapping (
        "id" -> ignored(1234),
        "amount" -> number,
        "summary" -> text,
        "tags" -> optional(text),
        "category" -> optional(text)
      )(ExpenseItem.apply)(ExpenseItem.unapply)
  )

  def add = Action { implicit request =>
    expenseForm.bindFromRequest.fold(
        hasErrors => BadRequest(views.html.expense.create(hasErrors)),
        sucess => {
          ExpenseItem.create(expenseForm.bindFromRequest.get)
          Redirect(routes.ExpenseController.list)
        }
    )
  }
  
  def list = Action {
    Ok(views.html.expense.list(ExpenseItem.all()))
  }
  
  def show(id: Int) = Action {
    Ok(views.html.expense.show(ExpenseItem.get(id)))
  }
  
  def delete(id: Int) = TODO
  
  def edit(id: Int) = TODO
  
  def create = Action {
    Ok(views.html.expense.create(expenseForm))
  }
}