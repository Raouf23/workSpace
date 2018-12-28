package stockNSql


//+-------------+---------------+------+-----+---------+-------+
//| Field       | Type          | Null | Key | Default | Extra |
//+-------------+---------------+------+-----+---------+-------+
//| stockticker | varchar(10)   | NO   | PRI |         |       |
//| tradedate   | varchar(30)   | NO   | PRI |         |       |
//| openprice   | decimal(10,2) | YES  |     | NULL    |       |
//| highprice   | decimal(10,2) | YES  |     | NULL    |       |
//| lowprice    | decimal(10,2) | YES  |     | NULL    |       |
//| closeprice  | decimal(10,2) | YES  |     | NULL    |       |
//| volume      | bigint(20)    | YES  |     | NULL    |       |
//+-------------+---------------+------+-----+---------+-------+


case class stocks(stockticker :String,
  tradedate :String ,
  openprice : Float,
  highprice : Float,
  lowprice : Float,
  closeprice : Float,
  volume : Long
)