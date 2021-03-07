create view prikaz_proizvoda_dobavljac_BanjaLuka(Dobavljac,Adresa, Mjesto, Proizvod) as
select d.NazivD, d.Adresa, d.MJESTO_BrojPoste , pr.NazivPr
from dobavljac d
inner join proizvod pr on d.JIB_Dobavljaca=pr.DOBAVLJAC_JIB_Dobavljaca
where d.MJESTO_BrojPoste=78000;

create view prikaz_proizvoda_proizvodjac_BanjaLuka(Proizvodjac, Adresa, Mjesto, Proizvod) as
select p.NazivP, p.Adresa, p.MJESTO_BrojPoste, pr.NazivPr
from proizvodjac p
inner join proizvod pr on p.JIB_Proizvodjaca=pr.PROIZVODJAC_JIB
where p.MJESTO_BrojPoste=78000;

create view prikaz_proizvoda_dobavljac_proizvodjac(Dobavljac , Proizvodjac, Proizvod, Mjesto) as
select d.NazivD, p.NazivP, pr.NazivPr, d.MJESTO_BrojPoste
from proizvod pr
inner join dobavljac d on d.JIB_Dobavljaca=pr.DOBAVLJAC_JIB_Dobavljaca
inner join proizvodjac p on p.JIB_Proizvodjaca=pr.PROIZVODJAC_JIB
where d.MJESTO_BrojPoste=p.MJESTO_BrojPoste;

drop view if exists zaposleni_datum_2021 ;
create view zaposleni_datum_2021(JMB, Ime, Prezime, Plata, DatumOd) as
select JMB_Zaposlenog, Ime, Prezime, Plata, DatumOd
from zaposleni 
where DatumOd between '2021-01-01' and '2021-12-31'
order by JMB_Zaposlenog asc;

drop view if exists zaposleni_datum_2020 ;
create view zaposleni_datum_2020(JMB, Ime, Prezime, Plata, DatumOd) as
select JMB_Zaposlenog, Ime, Prezime, Plata, DatumOd
from zaposleni 
where DatumOd between '2020-01-01' and '2020-12-31'
order by JMB_Zaposlenog asc;