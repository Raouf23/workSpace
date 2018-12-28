package sqlTest


//+---------------------+--------------+------+-----+---------+----------------+
//| Field               | Type         | Null | Key | Default | Extra          |
//+---------------------+--------------+------+-----+---------+----------------+
//| product_id          | int(11)      | NO   | PRI | NULL    | auto_increment |
//| product_category_id | int(11)      | NO   |     | NULL    |                |
//| product_name        | varchar(45)  | NO   |     | NULL    |                |
//| product_description | varchar(255) | NO   |     | NULL    |                |
//| product_price       | float        | NO   |     | NULL    |                |
//| product_image       | varchar(255) | NO   |     | NULL    |                |
//+---------------------+--------------+------+-----+---------+----------------+

case class Products (
  product_id: Int,
  product_category_id :Int,
  product_name: String,
  product_description :String,
  product_image :String
)