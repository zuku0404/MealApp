--liquibase formatted sql
--changeset zuku:1

create table cuisine (
    id bigint not null primary key auto_increment,
    name varchar(255)
);

create table ingredient (
    id bigint not null primary key auto_increment,
    name varchar(255) unique,
    ingredient_category varchar(255)
);

create table meal (
    id bigint not null primary key auto_increment,
    cuisine_id bigint,
    name varchar(255) unique,
    description varchar(255),
    image_url varchar(250),
    constraint meal_cuisine_fk foreign key (cuisine_id) references cuisine(id)
);

create table meal_composition (
    id bigint not null primary key auto_increment,
    ingredient_id bigint,
    meal_id bigint,
    count double,
    unit varchar(255),
    constraint meal_composition_ingredient_fk foreign key (ingredient_id) references ingredient(id),
    constraint meal_composition_meal_fk foreign key (meal_id) references meal(id)
);

create table account (
    id bigint primary key auto_increment,
    login varchar(255),
    password varchar(255)
);

create table family_preference (
    id bigint not null primary key auto_increment
);

create table family (
    id bigint primary key auto_increment,
    name varchar(255),
    preference_id bigint,
    constraint family_preference_fk foreign key (preference_id) references family_preference(id)
);

create table user (
    id bigint primary key auto_increment,
    account_id bigint,
    current_family_id bigint,
    name varchar(255),
    role varchar(255),
    constraint user_account_fk foreign key (account_id) references account(id),
    constraint user_current_family_fk foreign key (current_family_id) references family(id)
);

create table user_family (
    family_id bigint not null,
    user_id bigint not null,
    constraint user_family_family_fk foreign key (family_id) references family(id),
    constraint user_family_user_fk foreign key (user_id) references user(id)
);

create table family_ingredient (
    id bigint primary key auto_increment,
    family_id bigint,
    name varchar(255),
    ingredient_category varchar(255),
    constraint family_ingredient_family_fk foreign key (family_id) references family(id)
);

create table family_meal (
    id bigint not null primary key auto_increment,
    family_id bigint,
    cuisine_id bigint,
    name varchar(255),
    description varchar(255),
    image_url varchar(250),
    constraint family_meal_cuisine_fk foreign key (cuisine_id) references cuisine(id),
    constraint family_meal_family_fk foreign key (family_id) references family(id)
);

create table family_meal_composition (
    id bigint not null primary key auto_increment,
    ingredient_id bigint,
    family_ingredient_id bigint,
    family_meal_id bigint,
    count double,
    unit varchar(255),
    constraint family_meal_composition_ingredient_fk foreign key (ingredient_id) references ingredient(id),
    constraint family_meal_composition_family_ingredient_fk foreign key (family_ingredient_id) references family_ingredient(id),
    constraint family_meal_composition_family_meal_fk foreign key (family_meal_id) references family_meal(id)
    );

create table family_preference_cuisine (
    cuisine_id bigint not null,
    family_preference_id bigint not null,
    constraint family_preferences_cuisine_cuisine_fk foreign key (cuisine_id) references cuisine(id),
    constraint family_preferences_cuisine_family_preference_fk foreign key (family_preference_id) references family_preference(id)
);

create table family_preference_without_ingredient (
    ingredient_id bigint not null,
    family_preference_id bigint not null,
    constraint family_preference_without_ingredient_ingredient_fk foreign key (ingredient_id) references ingredient(id),
    constraint family_preference_without_ingredient_family_preference_fk foreign key (family_preference_id) references family_preference(id)
);

create table family_preference_without_family_ingredient (
    family_ingredient_id bigint not null,
    family_preference_id bigint not null,
    constraint family_preference_without_family_ingredient_family_ingredient_fk foreign key (family_ingredient_id) references family_ingredient(id),
    constraint family_preference_without_family_ingredient_family_preference_fk foreign key (family_preference_id) references family_preference(id)
);

create view global_family_ingredients_view as
    select ROW_NUMBER() OVER (ORDER BY source, id) AS row_id, id, name, source, family_id, ingredient_category
    from (
    select id, name, 'GLOBAL' as source, null as family_id, ingredient_category from ingredient
    union all
    select id, name, 'FAMILY' as source, family_id, ingredient_category from family_ingredient) AS combined;

create view global_family_meals_view as
    select ROW_NUMBER() OVER (ORDER BY source, id) AS row_id, id as meal_id, family_id, name, image_url, description, source
    from (
    select id, name, null as family_id, image_url, 'GLOBAL' as source, description from meal
    union all
    select id, name, family_id, image_url, 'FAMILY' as source, description from family_meal) AS combined;


