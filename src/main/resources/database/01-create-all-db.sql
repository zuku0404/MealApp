--liquibase formatted sql
--changeset zuku:1

create table cuisine (
    id bigint not null primary key auto_increment,
    name varchar(255)
--    name enum('ITALIAN','POLISH')
);

create table ingredient (
    id bigint not null primary key auto_increment,
    name varchar(255) unique,
    ingredient_category varchar(255)
--    ingredient_category enum('MEAT','VEGAN','VEGETARIAN')
);

create table meal (
    id bigint not null primary key auto_increment,
    cuisine_id bigint,
    name varchar(255) unique,
    description varchar(255),
    constraint meal_cuisine_fk foreign key (cuisine_id) references cuisine(id)
);

create table meal_ingredient (
    id bigint not null primary key auto_increment,
    ingredient_id bigint,
    meal_id bigint,
    count double,
    unit varchar(255),
--    unit enum('CUP','GRAM','KILOGRAM','LITER','MILLILITER','PIECE','TABLESPOON','TEASPOON'),
    constraint meal_ingredient_ingredient_fk foreign key (ingredient_id) references ingredient(id),
    constraint meal_ingredient_meal_fk foreign key (meal_id) references meal(id)
);

create table account (
    id bigint primary key auto_increment,
    login varchar(255),
    password varchar(255)
);

create table user_preference (
    id bigint not null primary key auto_increment
);

create table user (
    id bigint primary key auto_increment,
    account_id bigint,
    preference_id bigint,
    name varchar(255),
    role varchar(255),
--    role enum('ADMIN','USER')
    constraint user_preference foreign key (preference_id) references user_preference(id),
    constraint user_account foreign key (account_id) references account(id)
);

create table user_ingredient (
    id bigint primary key auto_increment,
    user_id bigint,
    name varchar(255),
    ingredient_category varchar(255),
--    ingredient_category enum('MEAT','VEGAN','VEGETARIAN'),
    constraint user_ingredient_user_fk foreign key (user_id) references user(id)
);

create table user_meal (
    id bigint not null primary key auto_increment,
    user_id bigint,
    cuisine_id bigint,
    name varchar(255),
    description varchar(255),
    constraint user_meal_cuisine_fk foreign key (cuisine_id) references cuisine(id),
    constraint user_meal_user_fk foreign key (user_id) references user(id)
);

create table user_meal_ingredient (
    id bigint not null primary key auto_increment,
    ingredient_id bigint,
    user_ingredient_id bigint,
    user_meal_id bigint,
    count double,
    unit varchar(255),
--    unit enum('CUP','GRAM','KILOGRAM','LITER','MILLILITER','PIECE','TABLESPOON','TEASPOON'),
    constraint user_meal_ingredient_ingredient_fk foreign key (ingredient_id) references ingredient(id),
    constraint user_meal_ingredient_user_ingredient_fk foreign key (user_ingredient_id) references user_ingredient(id),
    constraint user_meal_ingredient_user_meal_fk foreign key (user_meal_id) references user_meal(id)
    );

create view ingredient_view as
    select ROW_NUMBER() OVER (ORDER BY source, id) AS row_id, id, name, source, user_id
    from (
    select id, name, 'GLOBAL' as source, null as user_id from ingredient
    union all
    select id, name, 'USER' as source, user_id from user_ingredient) AS combined;

create table user_preference_cuisine (
    cuisine_id bigint not null,
    user_preference_id bigint not null,
    constraint user_preferences_cuisine_cuisine_fk foreign key (cuisine_id) references cuisine(id),
    constraint user_preferences_cuisine_user_preference_fk foreign key (user_preference_id) references user_preference(id)
);

create table user_preference_without_ingredient (
    ingredient_id bigint not null,
    user_preference_id bigint not null,
    constraint user_preference_without_ingredient_ingredient_fk foreign key (ingredient_id) references ingredient(id),
    constraint user_preference_without_ingredient_user_preference_fk foreign key (user_preference_id) references user_preference(id)
);

create table user_preference_without_user_ingredient (
    user_ingredient_id bigint not null,
    user_preference_id bigint not null,
    constraint user_preference_without_user_ingredient_user_ingredient_fk foreign key (user_ingredient_id) references user_ingredient(id),
    constraint user_preference_without_user_ingredient_user_preference_fk foreign key (user_preference_id) references user_preference(id)
);


