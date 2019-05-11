//import sbt.Keys._
// import scoverage.ScoverageKeys._
//import com.typesafe.sbt.pgp.PgpKeys._

//object Build extends Build {

  val dependencyResolvers = Seq("Typesafe Maven Repository" at "http://repo.typesafe.com/typesafe/maven-releases/")

  val dependencies = Seq(
    "org.scala-lang" % "scala-reflect" % "2.12.8",
    "com.typesafe.slick" %% "slick" % "3.3.0",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    //"com.typesafe.slick" %% "slick-extensions" % "3.1.0" % "test",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0" % "test",
    "org.scalatest" %% "scalatest" % "3.0.0" % "test",
    "com.h2database" % "h2" % "1.4.192" % "test",
    "mysql" % "mysql-connector-java" % "5.1.38" % "test",
    "org.postgresql" % "postgresql" % "9.4.1211" % "test",
    "org.slf4j" % "slf4j-simple" % "1.7.21" % "test",
    "org.apache.derby" % "derby" % "10.11.1.1" % "test",
    "org.hsqldb" % "hsqldb" % "2.3.4" % "test",
    "joda-time" % "joda-time" % "2.9.6" % "test"
  )

  lazy val core =
    (project in file("."))
    .configs(AllDbsTest, Db2Test, SqlServerTest)
    .settings(inConfig(AllDbsTest)(Defaults.testTasks): _*)
    .settings(inConfig(Db2Test)(Defaults.testTasks): _*)
    .settings(inConfig(SqlServerTest)(Defaults.testTasks): _*)
      .settings(

        name := "slick-repository",
        description := "CRUD Repositories for Slick based persistence Scala projects",
        version := "1.0.2",

        scalaVersion := "2.12.8",
        crossScalaVersions := Seq("2.12.6", "2.11.12", "2.10.7"),
        libraryDependencies ++= dependencies,
        resolvers ++= dependencyResolvers,
        parallelExecution in Test := false,
        coverageEnabled := true,
        testOptions in Test := Seq(Tests.Filter(baseFilter)),
        testOptions in Db2Test := Seq(Tests.Filter(db2Filter)),
        testOptions in AllDbsTest := Seq(Tests.Filter(allDbsFilter)),
        testOptions in SqlServerTest := Seq(Tests.Filter(sqlServerFilter)),

        publishMavenStyle := true,
        bintrayOrganization := Some("spread"),
        bintrayRepository := "slick-repositories",


        organization:= "com.spread0x",
        pomIncludeRepository := { _ => false },
        publishArtifact in Test := false,
        //publishTo := sonatypePublishTo.value
        licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
/*
        publishTo := {
          val nexus = "https://oss.sonatype.org/"
          if (isSnapshot.value)
            Some("snapshots" at nexus + "content/repositories/snapshots")
          else
            Some("releases"  at nexus + "service/local/staging/deploy/maven2")
        },
*/        
        credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
        useGpg := true,
        pomExtra :=
          <url>https://github.com/spread0x/slick-repositories</url>
          <inceptionYear>2019</inceptionYear>
          <licenses>
            <license>
              <name>MIT License</name>
              <url>https://opensource.org/licenses/MIT</url>
            </license>
          </licenses>
          <developers>
            <developer>
              <id>spread0x</id>
              <name>Stanislav Sobolev</name>
              <url>https://github.com/spread0x</url>
            </developer>
          </developers>
          <scm>
            <url>https://github.com/spread0x/slick-repositories.git</url>
            <connection>scm:git:git://github.com/spread0x/slick-repositories.git</connection>
          </scm>
      )

lazy val mysql: Project =
    (project in file("src/docker/mysql"))
      .settings(
        name := "mysql"
      )

  /*
  lazy val oracleBuild: Project =
    (project in file("src/docker/oracle-build"))
      .settings(
        name := "oracle-build"
      )

  lazy val db2: Project =
    (project in file("src/docker/db2"))
      .settings(
        name := "db2"
      )
  */

  lazy val postgres: Project =
    (project in file("src/docker/postgres"))
      .settings(
        name := "postgres"
      )      

  val dbPrefixes = Seq("MySQL", "Oracle", "Postgres", "Derby", "Hsql")
  val db2Prefix = Seq("DB2")
  val sqlServerPrefix = Seq("SQLServer")
  lazy val AllDbsTest: Configuration = config("alldbs") extend Test
  lazy val Db2Test: Configuration = config("db2") extend Test
  lazy val SqlServerTest: Configuration = config("sqlserver") extend Test

  def testName(name: String): String = name.substring(name.lastIndexOf('.') + 1)

  def allDbsFilter(name: String): Boolean = dbPrefixes.exists(p => testName(name) startsWith p)

  def db2Filter(name: String): Boolean = db2Prefix.exists(p => testName(name) startsWith p)

  def sqlServerFilter(name: String): Boolean = sqlServerPrefix.exists(p => testName(name) startsWith p)

  def baseFilter(name: String): Boolean = !allDbsFilter(name) && !db2Filter(name) && !sqlServerFilter(name)

