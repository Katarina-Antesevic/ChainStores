INSERT INTO mjesto VALUES (78000, "Banja Luka");
INSERT INTO mjesto VALUES (79101, "Prijedor");
INSERT INTO mjesto VALUES (76300, "Bijeljina");

insert into banka values(1,"Nova Banka" ,051986598, "Jovana Cvijica 5", 78000);
insert into banka values(2,"Reiffeisen" ,051789548, "Milana Tepica 12", 78000);


insert into organizaciona_jedinica values(1,"oj1@gmail.com","Zmaj Jove 5", 78000);
insert into organizaciona_jedinica values(2,"oj2@gmail.com","Zmaj Jove 6", 78000);
insert into organizaciona_jedinica values(3,"oj3@gmail.com","Prijedorska 19", 79101);
insert into organizaciona_jedinica values(4,"oj4@gmail.com","Prijedorska 20", 79101);




insert into telefon_organizacione_jedinice values (1, 051898569);
insert into telefon_organizacione_jedinice values (1, 051689589);
insert into telefon_organizacione_jedinice values (1, 051369589);
insert into telefon_organizacione_jedinice values (2, 051356895);
insert into telefon_organizacione_jedinice values (3, 052689568);
insert into telefon_organizacione_jedinice values (3, 052364895);
insert into telefon_organizacione_jedinice values (4, 052658965);



insert into magacin values(2, "Magacin1");
insert into magacin values(4, "Magacin2");

insert into prodavnica values(1 , "Shop 1");
insert into prodavnica values(3 , "Shop 2");



insert into kupac values (1, "infocom@gmail.com" ,"Kralja Alfonsa III 26" , 78000);
insert into preduzece values(1, "1112223334568" , "Infocom");
insert into kupac values (2, "katarinaa@gmail.com", "Lovcenska 102" ,78000);
insert into individualni values( 2, "2103997105002" , "Katarina", "Antesevic");

insert into kupac values (3, "marija@gmail.com", "Goluba Babica 6" ,78000);
insert into individualni values( 3, "2568954896589" , "Marija", "Jekic");
insert into kupac values (4, "aleksandra@gmail.com", "Goluba Babica 6" ,78000);
insert into individualni values( 4, "2789568569232" , "Aleksandra", "Jekic");

insert into dobavljac values("4589652365895", "DD Banja Luka","dd@gmail.com",051689568,"Milana Tepica 65",78000);
insert into dobavljac values("4589623568758", "Dostava BL","dbl@gmail.com",051658965,"Sargovacka 89",78000);
insert into dobavljac values("8956245875696", "DD Prijedor","ddp@gmail.com",052689589,"Proleterska 54",79101);

insert into racun_dobavljaca values("9658945258654","4589652365895",1);
insert into racun_dobavljaca values("6258954785125","4589623568758",2);
insert into racun_dobavljaca values("1257859658745","8956245875696",2);

insert into proizvodjac values("1256369854785", "Proizvodjac1" ,"p1@gmail.com",051699658,"Sargovacka 77",78000);
insert into proizvodjac values("5625895785426", "Proizvodjac2" ,"p2@gmail.com",051546545,"Sargovacka 109",78000);
insert into proizvodjac values("8952355465465", "Proizvodjac3" ,"p3@gmail.com",051545312,"Banjalucka 100",78000);
insert into proizvodjac values("4541313516544", "Proizvodjac4" ,"p4@gmail.com",055513132,"Bijeljinska 177",76300);
insert into proizvodjac values("6543513456511", "Proizvodjac5" ,"p5@gmail.com",055351354,"Bijeljinska 77",76300);


insert into proizvod values("125698548759", "Klima uredjaj", 505 ,1256369854785,4589652365895);
insert into proizvod values("895685236520", "Polica", 67.50 ,5625895785426,4589652365895);
insert into proizvod values("845230125023", "Lampa", 33.45 ,1256369854785,4589623568758);
insert into proizvod values("136020150000", "Brasno", 20 ,4541313516544,8956245875696);
insert into proizvod values("012598623402", "Uticnica", 5.45 ,6543513456511,4589623568758);

insert into proizvod_u_organizacionoj_jedinici values("125698548759",1,25);
insert into proizvod_u_organizacionoj_jedinici values("895685236520",1,35);
insert into proizvod_u_organizacionoj_jedinici values("845230125023",1,20);
insert into proizvod_u_organizacionoj_jedinici values("136020150000",1,90.50);
insert into proizvod_u_organizacionoj_jedinici values("012598623402",1,23);

insert into proizvod_u_organizacionoj_jedinici values("125698548759",2,50);
insert into proizvod_u_organizacionoj_jedinici values("895685236520",2,100);
insert into proizvod_u_organizacionoj_jedinici values("845230125023",2,55);
insert into proizvod_u_organizacionoj_jedinici values("136020150000",2,200.50);
insert into proizvod_u_organizacionoj_jedinici values("012598623402",2,250);

insert into proizvod_u_organizacionoj_jedinici values("125698548759",3,30);
insert into proizvod_u_organizacionoj_jedinici values("895685236520",3,20);
insert into proizvod_u_organizacionoj_jedinici values("845230125023",3,12);
insert into proizvod_u_organizacionoj_jedinici values("136020150000",3,45.50);
insert into proizvod_u_organizacionoj_jedinici values("012598623402",3,60);

insert into proizvod_u_organizacionoj_jedinici values("125698548759",4,100);
insert into proizvod_u_organizacionoj_jedinici values("895685236520",4,90);
insert into proizvod_u_organizacionoj_jedinici values("845230125023",4,70);
insert into proizvod_u_organizacionoj_jedinici values("136020150000",4,320.50);
insert into proizvod_u_organizacionoj_jedinici values("012598623402",4,150);


insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values ("1256897854635", "Ivan", "Markovic","ivan.m@gmail.com",065895685,1500,"Brace Jugovic 89", 78000);
insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("1325698758423", "Ivan", "Mitrovic","ivan.m@gmail.com",065895685,1500,"Brace Jugovic 89", 78000);
insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("5865854265895", "Danica", "Picar","danica.p@gmail.com",066856478,1000,"Sargovacka 9", 78000);
insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("1452369854785", "Ilija", "Djukanovic","ilija.dj@gmail.com",066598754,1000,"Mihajla Petrovica Alasa 17", 78000);
insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("2356895478545", "Marko", "Petkovic","marko.p@gmail.com",066895785,1500,"Lovcenska 27", 79101);
insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("1256856895896", "Darko", "Mitrovic","darko.m@gmail.com",066859758,1500,"Omladinska 23", 79101);
insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("3652985698547", "Branka", "Djukic","branka.dj@gmail.com",066895478,1000,"Omladinska 1", 79101);
insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("7854562356985", "Mirko", "Mitrovic","mirko.m@gmail.com",065895879,1000,"Omladinska 19", 79101);
insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("5646543216845", "Danijel", "Pasic","danijel.p@gmail.com",065454258,1100,"Omladinska 26", 79101);

insert into zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste) values("4654654654545", "Nikola", "Nikolic","nikola.n@gmail.com",065554586,950,"Omladinska 45", 79101);



insert into administratovni_radnik value ("1256897854635" );
insert into administratovni_radnik value ("1325698758423" );
insert into administratovni_radnik value ("2356895478545" );
insert into administratovni_radnik value ("1256856895896" );

insert into organizaciona_jedinica_ima_administratovnog_radnika value (1,"1256897854635");
insert into organizaciona_jedinica_ima_administratovnog_radnika value (2,"1256897854635");
insert into organizaciona_jedinica_ima_administratovnog_radnika value (3,"1325698758423");
insert into organizaciona_jedinica_ima_administratovnog_radnika value (4,"2356895478545");
insert into organizaciona_jedinica_ima_administratovnog_radnika value (4,"1256856895896");


insert into magacioner values ("1452369854785", 2);
insert into magacioner values ("5646543216845", 2);
insert into magacioner values ("7854562356985", 4);

insert into trgovac values("5865854265895",1);
insert into trgovac values("3652985698547",3);
insert into trgovac values("4654654654545",1);
