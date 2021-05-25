
create table person (
    id serial not null,
    email varchar(255) default null,
    name varchar(255) default null,
    password varchar(255) default null,
    username varchar(255) default null,
    primary key (id)
);

create table product (
    id serial not null,
    category varchar(255) default null,
    count_product int8 default null,
    description varchar(255) default null,
    img varchar(255) default null,
    manufacturer varchar(255) default null,
    name varchar(255) default null,
    price int8 default null,
    primary key (id)
);

create table review (
    id serial not null,
    create_date timestamp default null,
    rating float8 default null,
    text varchar(255) default null,
    person_id int8 not null,
    product_id int8 not null,
    primary key (id),
    foreign key (person_id) references product (id),
    foreign key (product_id) references product (id)
);

create table orderr (
    id serial not null,
    create_date timestamp default null,
    status varchar(255) default null,
    text varchar(255) default null,
    store varchar(255) not null,
    person_id int8 not null,
    foreign key (person_id) references product (id),
    primary key (id)
);

create table order_product (
    order_id int8 not null,
    product_id int8 not null,
    primary key (order_id,product_id),
    foreign key (order_id) references orderr (id),
    foreign key (product_id) references product (id)
);

create table person_product (
    person_id int8,
    product_id int8,
    primary key (person_id,product_id),
    foreign key (person_id) references product (id),
    foreign key (product_id) references product (id)
);