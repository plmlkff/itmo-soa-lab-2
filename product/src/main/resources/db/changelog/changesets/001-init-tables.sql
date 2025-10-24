create table coordinates
(
    id serial primary key,
    y  float4,
    x  bigint not null,
    constraint coordinates_y_min_value check ( y > -893 )
);

create table persons
(
    id          serial primary key,
    name        varchar(255) not null,
    birthday    date,
    nationality varchar(255)
);

create table products
(
    id               serial primary key,
    coordinates_id   integer      not null unique,
    manufacture_cost float4,
    owner_id         integer      not null,
    price            integer      not null,
    creation_date    date         not null,
    name             varchar(255) not null,
    unit_of_measure  varchar(255),
    constraint products_price_min_value_constr check ( price > 0 ),
    constraint products_coordinates_id_fk foreign key (coordinates_id) references coordinates (id) on update restrict on delete restrict,
    constraint products_owner_id_fk foreign key (owner_id) references persons (id) on update restrict on delete restrict
);