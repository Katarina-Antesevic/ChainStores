delimiter $$
create procedure maksimalna_plata_zaposlenog_u_Prodavnici
(in idProdavnice int,out maksimalna_plata decimal(7,2))
begin
	select max(Plata) into maksimalna_plata
    from zaposleni
    inner join trgovac T on T.ZAPOSLENI_JMB_Zaposlenog=JMB_Zaposlenog
    where T.PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=idProdavnice;
end$$
delimiter ;


delimiter $$
create procedure maksimalna_plata_zaposlenog_u_Magacinu
(in idMagacina int,out maksimalna_plata_Magacin decimal(7,2))
begin
	select max(Plata) into maksimalna_plata_Magacin
    from zaposleni
    inner join magacioner M on M.ZAPOSLENI_JMB_Zaposlenog=JMB_Zaposlenog
    where M.MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=idMagacina;
end$$
delimiter ;



delimiter $$
create procedure minimalna_plata_zaposlenog_u_Prodavnici
(in idProdavnice int,out maksimalna_plata decimal(7,2))
begin
	select min(Plata) into maksimalna_plata
    from zaposleni
    inner join trgovac T on T.ZAPOSLENI_JMB_Zaposlenog=JMB_Zaposlenog
    where T.PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=idProdavnice;
end$$
delimiter ;


delimiter $$
create procedure minimalna_plata_zaposlenog_u_Magacinu
(in idMagacina int,out minimalna_plata decimal(7,2))
begin
	select min(Plata) into minimalna_plata
    from zaposleni
    inner join magacioner M on M.ZAPOSLENI_JMB_Zaposlenog=JMB_Zaposlenog
    where M.MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=idMagacina;
end$$
delimiter ;


delimiter $$
create procedure prosjecna_plata_zaposlenog_u_Magacinu
(in idMagacina int,out maksimalna_plata decimal(7,2))
begin
	select avg(Plata) into maksimalna_plata
    from zaposleni
    inner join magacioner M on M.ZAPOSLENI_JMB_Zaposlenog=JMB_Zaposlenog
    where M.MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=idMagacina;
end$$
delimiter ;

-- call prosjecna_plata_zaposlenog_u_Magacinu(4,@m);
-- select @m;


delimiter $$
create procedure prosjecna_plata_zaposlenog_u_Prodavnici
(in idProdavnice int,out maksimalna_plata decimal(7,2))
begin
	select avg(Plata) into maksimalna_plata
    from zaposleni
    inner join trgovac T on T.ZAPOSLENI_JMB_Zaposlenog=JMB_Zaposlenog
    where T.PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=idProdavnice;
end$$
delimiter ;

-- call prosjecna_plata_zaposlenog_u_Prodavnici(1,@m);
-- select @m;

delimiter $$
create procedure minimalna_plata_Zaposlenog(out  minimalna_plata decimal(7,2))
begin
	select min(Plata) into minimalna_plata
    from zaposleni;
end$$
delimiter ;

delimiter $$
create procedure maksimalna_plata_Zaposlenog
(out maksimalna_plata decimal(7,2))
begin
	select max(Plata) into maksimalna_plata
    from zaposleni;
end$$
delimiter ;

-- call maksimalna_plata_Zaposlenog(@max);
-- select @max;
