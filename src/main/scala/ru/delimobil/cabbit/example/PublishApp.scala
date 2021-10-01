package ru.delimobil.cabbit.example

import cats.data.NonEmptyList
import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import io.circe.generic.auto._
import ru.delimobil.cabbit.ConnectionFactoryProvider
import ru.delimobil.cabbit.encoder.json.jsonUtf8
import ru.delimobil.cabbit.model.CabbitConfig
import ru.delimobil.cabbit.model.CabbitConfig.CabbitNodeConfig
import ru.delimobil.cabbit.model.ExchangeName
import ru.delimobil.cabbit.model.RoutingKey

object PublishApp extends IOApp {

  case class Message(from: String, msg: String)

  def run(args: List[String]): IO[ExitCode] =
    ArgsValidator.validate(args) match {
      case Left(value)         => IO.println(value).as(ExitCode.Error)
      case Right((host, port)) => push(host, port).as(ExitCode.Success)
    }

  private def push(host: String, port: Int): IO[Unit] = {
    val node = CabbitNodeConfig(host, port)
    val config = CabbitConfig(NonEmptyList.one(node), "/")
    val exchange = ExchangeName.default
    val rk = RoutingKey("teaser-example-queue")
    val msg = Message("TeaserPublishApp", "hello from cabbit")
    ConnectionFactoryProvider
      .provide[IO](config, sslContext = None)
      .newConnection(config.addresses)
      .flatMap(_.createChannelPublisher)
      .use(_.basicPublish(exchange, rk, msg) *> log("published"))
  }

  private def log(s: String): IO[Unit] = IO.println(s"publisher: $s")
}
