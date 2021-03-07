drop trigger if exists zaposleni_insert_trigger ;
create trigger zaposleni_insert_trigger before insert
on zaposleni
for each row
set new.DatumOd=now();
