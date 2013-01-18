package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._
import java.sql.Connection
import org.squeryl.internals.DatabaseAdapter
import com.typesafe.config._
import play.api.Play.current
import java.util.{Locale, TimeZone}
import org.squeryl.adapters.OracleAdapter
import org.squeryl.adapters.H2Adapter

object Tables extends ActiveRecordTables with PlaySupport {
	val expenses = table[Expense]
}

trait PlaySupport { self: ActiveRecordTables =>
  override def loadConfig(config: Map[String, Any]): ActiveRecordConfig = {
    new PlayConfig(config) 
  }
  class PlayConfig(
    overrideSettings: Map[String, Any] = Map()
  ) extends ActiveRecordConfig {
    def getString(key: String, default: String): String =
      overrideSettings.get(key).map(_.toString).orElse(
        current.configuration.getString(key)
      ).getOrElse(default)

    override def schemaClass: String = {
      getString("activerecord.schema", "models.Tables")
    }

    override def connection: Connection =
      play.api.db.DB.getConnection("activerecord")

    lazy val adapter: DatabaseAdapter = adapter(getString("db.activerecord.driver", "org.h2.Driver"))

    override def translator: i18n.Translator = PlayTranslator

    def timeZone: TimeZone = TimeZone.getDefault
  }

  object PlayTranslator extends i18n.Translator {
    import play.api.i18n._

    def get(key: String, args: Any*)(implicit locale: Locale):Option[String] = {
      implicit val lang = Lang(locale.getLanguage)
      if (Messages.messages.get(lang.code).exists(_.isDefinedAt(key))) {
        Some(Messages(key, args:_*))
      } else {
        i18n.DefaultTranslator.get(key)(locale)
      }
    }
  }
}