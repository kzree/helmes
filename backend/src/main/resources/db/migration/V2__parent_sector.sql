alter table sector
  add column parent_id uuid
    constraint fk_sector__parent_id
      references sector (id) on delete set null;
