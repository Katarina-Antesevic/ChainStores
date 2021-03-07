-- korisnicki nalog
create user 'referent'@'localhost' identified by 'referent';
grant select, insert, update, delete on lanac_prodavnica.* to 'referent'@'localhost';
grant execute on procedure lanac_prodavnica.prosjecna_plata_zaposlenog_u_Prodavnici to 'referent'@'localhost';
grant execute on procedure lanac_prodavnica.prosjecna_plata_zaposlenog_u_Magacinu to 'referent'@'localhost';
grant execute on procedure lanac_prodavnica.maksimalna_plata_zaposlenog_u_Prodavnici to 'referent'@'localhost';
grant execute on procedure lanac_prodavnica.minimalna_plata_zaposlenog_u_Prodavnici to 'referent'@'localhost';
grant execute on procedure lanac_prodavnica.maksimalna_plata_zaposlenog_u_Magacinu to 'referent'@'localhost';
grant execute on procedure lanac_prodavnica.minimalna_plata_zaposlenog_u_Magacinu to 'referent'@'localhost';


flush privileges;
