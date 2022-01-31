create table user_demo
(
    id          bigint not null
        constraint user_pkey
            primary key,
    name text,
    email        text,
    password        text,
    created date,
    last_login date,
    token text
);

create sequence user_demo_sequence;

create table phone
(
    id               bigint not null
        constraint phone_pkey
            primary key,
    number           bigint,
    city_code   int,
    user_id bigint,
    country_code             text,

    CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user_demo (id)


);

create sequence phone_sequence;
