package sqlTest

//+-----------------+-------------+------+-----+---------+----------------+
//| Field           | Type        | Null | Key | Default | Extra          |
//+-----------------+-------------+------+-----+---------+----------------+
//| department_id   | int(11)     | NO   | PRI | NULL    | auto_increment |
//| department_name | varchar(45) | NO   |     | NULL    |                |
//+-----------------+-------------+------+-----+---------+----------------+



case class Departments(
    department_id : Int,
    department_name: String 
)