create table sector (
  id uuid not null primary key,
  name text not null,
  created_at timestamp not null,
  modified_at timestamp not null
);

create table input (
  id uuid not null primary key,
  name text not null,
  terms_accepted boolean not null default false,
  created_at timestamp not null,
  modified_at timestamp not null
);

create table rel_sector__input (
  sector_id uuid not null
    constraint fk_rel_sector__input__sector_id
      references sector (id),
  input_id uuid not null
    constraint fk_rel_sector__input__input_id
      references input (id),
  primary key (sector_id, input_id)
);
