create table brand (
    id bigint auto_increment primary key not null,
    name varchar(255) not null
);

create index brand_name_index
    on brand (name);

create table category (
    id bigint auto_increment primary key not null,
    name varchar(255) not null
);

create index category_name_index
    on category (name);

create table product (
    id bigint auto_increment primary key not null ,
    name varchar(255) not null,
    price int not null
);

create index product_name_index
    on product (name);

create index product_price_index
    on product (price);

create table product_brand (
    id         bigint auto_increment primary key not null ,
    product_id bigint not null,
    brand_id   bigint not null
);

create index product_brand_product_id_index
    on product_brand (product_id);

create index product_brand_brand_id_index
    on product_brand (brand_id);

create table product_category (
    id         bigint auto_increment primary key not null,
    product_id bigint not null,
    category_id bigint not null
);

create index product_category_product_id_index
    on product_category (product_id);

create index product_category_category_id_index
    on product_category (category_id);