--liquibase formatted sql
--changeset sosadnik:1

create sequence public.crafting_recipes_id_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 2147483647
    cache 1;

create table public.crafting_recipes (
    id integer not null primary key default nextval('crafting_recipes_id_seq'::regclass),
    name text not null,
    amount_ingredients integer not null,
    item_value integer not null,
    category text not null
);

