This is springboot demo, include MySQL, MyBatis for Restful API.

Note: DO NOT use number as column name, and also use underscore format.

Restriction: MUST have one column named id as primary key.

Rest API:
- GET ONE: GET HOST_IP:PORT/rest/<database_schema>/<table_name>/{id}
- GET ALL: GET HOST_IP:PORT/rest/<database_schema>/<table_name>
- INSERT ONE: POST HOST_IP:PORT/rest/<database_schema>/<table_name>
- INSERT BULK: POST HOST_IP:PORT/rest/<database_schema>/<table_name>/bulk
- UPDATE ONE: PUT HOST_IP:PORT/rest/<database_schema>/<table_name>/{id}
- DELETE ONE: DELETE HOST_IP:PORT/rest/<database_schema>/<table_name>/{id}

MyBatis Supported JDBC Types:
For future reference, MyBatis supports the following JDBC Types via the included JdbcType enumeration.

BIT	FLOAT	CHAR	TIMESTAMP	OTHER	UNDEFINED
TINYINT	REAL	VARCHAR	BINARY	BLOB	NVARCHAR
SMALLINT	DOUBLE	LONGVARCHAR	VARBINARY	CLOB	NCHAR
INTEGER	NUMERIC	DATE	LONGVARBINARY	BOOLEAN	NCLOB
BIGINT	DECIMAL	TIME	NULL	CURSOR	ARRAY
