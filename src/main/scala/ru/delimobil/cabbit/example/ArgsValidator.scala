package ru.delimobil.cabbit.example

import cats.syntax.either._
import ru.delimobil.cabbit.model.CabbitConfig.Host
import ru.delimobil.cabbit.model.CabbitConfig.Port

object ArgsValidator {

  def validate(args: List[String]): Either[String, (Host, Port)] =
    validateArgsNum(args).flatMap(validatePort).leftMap(errorMsg)

  private def validateArgsNum(args: List[String]): Either[String, (String, String)] =
    Either.cond(args.length == 2, (args.head, args(1)), s"expected host, port. Got $args")

  private def validatePort(tuple: (String, String)): Either[String, (Host, Port)] = {
    val (host, port) = tuple
    port.toIntOption.map((host, _)).toRight(s"expected port to be Int. Got $port")
  }

  private def errorMsg(err: String) =
    s"""Usage: TeaserApp $$host $$port
       |Example: localhost 5672
       |Error: $err""".stripMargin
}
