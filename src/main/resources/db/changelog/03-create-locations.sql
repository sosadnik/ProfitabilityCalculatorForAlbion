--liquibase formatted sql
--changeset sosadnik:1

create sequence public.locations_id_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 2147483647
    cache 1;

create table public.locations (
    id integer not null primary key default nextval('locations_id_seq'::regclass),
    name text not null
);