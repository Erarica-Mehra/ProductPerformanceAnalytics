create table analysed_product
(
  year int,
  day date,
  seller  text,
  category text,
  name text,
  id bigint,
  quantity int, 
  price double,
  rating int, 
  status text,
  description text,
  user text,
  primary key (year,day,seller,category,name, id)
)WITH CLUSTERING ORDER BY (day desc, seller desc, category desc, name desc, id desc);

create table products_by_seller
(
  year int,
  seller text,
  day date,
  name text,
  id bigint,
  quantity int,
  count int,
  price double,
  rating int,
  primary key (year,seller,day,name, id)
) WITH CLUSTERING ORDER BY (seller desc,day desc,name desc, id desc);


create table products_by_name
(
  year int,
  name text,
  day date,
  id bigint,
  count int,
  quantity int,
  price double,
  rating int,
  primary key (year,name,day,id)
) WITH CLUSTERING ORDER BY (name desc,day desc, id desc);


create table products_by_status
(
  year int,
  status text,
  day date,
  id bigint,
  count int,
  quantity int,
  price double,
  primary key (year,status,day,id)
) WITH CLUSTERING ORDER BY (status desc,day desc, id desc);


CREATE TABLE raw_product_data (
    year int,
    month int,
    timestamp timestamp,
    category text,
    description text,
    price double,
    id bigint,
    name text,
    quantity int,
    rating int,
    seller text,
    status text,
    user text,
    PRIMARY KEY ((year, month), timestamp)
) WITH CLUSTERING ORDER BY (timestamp DESC);


create table products_by_category
(
  year int,
  category text,
  day date,
  name text,
  id bigint,
  quantity int,
  count int,
  primary key (year,category,day,name, id)
) WITH CLUSTERING ORDER BY (category desc,day desc,name desc, id desc);

