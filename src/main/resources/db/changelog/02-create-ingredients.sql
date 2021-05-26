--liquibase formatted sql
--changeset sosadnik:1

create sequence public.ingredients_id_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 2147483647
    cache 1;

create sequence public.ingredients_fk_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 2147483647
    cache 1;

create TABLE public.ingredients (
    id integer not null primary key default nextval('ingredients_id_seq'::regclass),
    name text not null,
    amount integer not null,
    crafting_recipes_id integer not null default nextval('ingredients_fk_seq'::regclass),
    constraint crafting_recipes_fk foreign key (crafting_recipes_id)
    references public.crafting_recipes(id)
);