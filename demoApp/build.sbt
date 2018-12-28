name := "SparkExercises"
version := "1.0"

libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "5.1.24",
"org.apache.spark" % "spark-core_2.10" % "1.6.2",
"org.apache.spark" % "spark-sql_2.10" % "1.6.2",
"org.apache.spark" % "spark-hive_2.10" % "1.6.2",
"org.apache.spark" % "spark-streaming_2.10" % "1.6.2",
"org.apache.spark" % "spark-graphx_2.10" % "1.6.2",
"com.typesafe" % "config" % "1.3.1"
)