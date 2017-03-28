
-- person table needs more info.
alter table person
  add column ssn varchar(9) -- social security number
;

alter table person
  -- year the person was born
  add column born integer 
;

