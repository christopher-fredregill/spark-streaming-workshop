package minio

/**
  * @author christopher
  * @since 2019-09-23
  */
trait MinioEnv {
  val minioEndpoint   = s"http://${sys.env.getOrElse("MINIO_HOST", "127.0.0.1")}:${sys.env.getOrElse("MINIO_PORT", 9000)}"
  val minioAccessKey  = s"${sys.env.getOrElse("MINIO_ACCESS_KEY", "minio_access_key")}"
  val minioSecretKey  = s"${sys.env.getOrElse("MINIO_SECRET_KEY", "minio_secret_key")}"
}
