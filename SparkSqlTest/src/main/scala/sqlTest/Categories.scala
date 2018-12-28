package sqlTest


//+------------------------+-------------+------+-----+---------+----------------+
//| Field                  | Type        | Null | Key | Default | Extra          |
//+------------------------+-------------+------+-----+---------+----------------+
//| category_id            | int(11)     | NO   | PRI | NULL    | auto_increment |
//| category_department_id | int(11)     | NO   |     | NULL    |                |
//| category_name          | varchar(45) | NO   |     | NULL    |                |
//+------------------------+-------------+------+-----+---------+----------------+

case class Categories(
    category_id: Int,
    category_department_id : Int,
    category_name: String
)