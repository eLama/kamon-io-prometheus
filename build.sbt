
/* =========================================================================================
 * Copyright © 2013-2017 the kamon project <http://kamon.io/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * =========================================================================================
 */
import com.elama.sbthouserules.Resolvers

resolvers += Resolver.bintrayRepo("kamon-io", "snapshots")

val kamonCore    = "io.kamon" %% "kamon-core"     % "2.0.4"
val kamonTestKit = "io.kamon" %% "kamon-testkit"  % "2.0.4"
val nanohttpd    = "org.nanohttpd" % "nanohttpd"  % "2.3.1"
val scalaTest    = "org.scalatest" %% "scalatest" % "3.1.2"

lazy val root = (project in file("."))
  .settings(name := "kamon-prometheus", organization := "com.elama")
  .settings(
    libraryDependencies ++=
      compileScope(kamonCore, nanohttpd) ++
        testScope(scalatest, logbackClassic, kamonTestKit, scalaTest)
  )
  .settings(
    publishMavenStyle := true,
    publishTo := {
      val resolver = if (isSnapshot.value) {
        Resolvers.ElamaSnapshot
      } else {
        Resolvers.ElamaRelease
      }
      Some(resolver)
    },
    releaseTagName := {
      s"${name.value}-release-${version.value}"
    },
    scalaVersion := "2.13.2",
    crossScalaVersions := Seq("2.12.11", "2.13.2"),
    releaseCrossBuild := true
  )
