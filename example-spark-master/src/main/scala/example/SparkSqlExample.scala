// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package example

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/** Based on http://en.wikibooks.org/wiki/SQL_Exercises/Employee_management. */
object SparkSqlExample extends LazyLogging {

  private val master = "local[2]"
  private val appName = "example-spark"

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setMaster(master)
      .setAppName(appName)

    val sc = new SparkContext(conf)
    val sqlc = new HiveContext(sc)

    val employeeDao = new EmployeeDao(sqlc)
    val departmentDao = new DepartmentDao(sqlc)

    import sqlc.implicits._

    val employees = sc.textFile("src/main/resources/data/employees.txt")
      .map(_.split(","))
      .map(fields => Employee(fields(0), fields(1), fields(2), fields(3).trim.toInt))
      .toDF()
    employees.registerTempTable("employees")

    val departments = sc.textFile("src/main/resources/data/departments.txt")
      .map(_.split(","))
      .map(fields => Department(fields(0).trim.toInt, fields(1), fields(2).trim.toInt))
      .toDF()
    departments.registerTempTable("departments")

    logger.info("Select the last name of all employees")
    employeeDao.lastNames().collect().foreach(logger.info(_))

    logger.info("Select the last name of all employees, without duplicates.")
    employeeDao.distinctLastNames().collect().foreach(logger.info(_))

    logger.info("Select all the data of employees whose last name is \"Smith\".")
    employeeDao.byLastName("Smith").collect().map(_.toString) foreach (logger.info(_))

    logger.info("Select all the data of employees whose last name is \"Smith\" or \"Doe\".")
    employeeDao.byLastName("Smith", "Doe").collect().map(_.toString).foreach(logger.info(_))

    logger.info("Select all the data of employees whose last name begins with an \"S\".")
    employeeDao.byLastNameLike("S").collect().map(_.toString).foreach(logger.info(_))

    logger.info("Select the sum of all the departments' budgets.")
    logger.info(departmentDao.sumBudgets().toString)

    logger.info("Select the number of employees in each department.")
    departmentDao.numberOfEmployees().collect().map(_.toString()).foreach(logger.info(_))

    logger.info("Select all the data of employees, including each employee's department's data.")
    val employeesWithDepartments = employeeDao.withDepartment()
    employeesWithDepartments.collect().map(_.toString).foreach(logger.info(_))

  }

}
