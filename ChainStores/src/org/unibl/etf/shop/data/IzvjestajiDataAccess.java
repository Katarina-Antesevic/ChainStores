package org.unibl.etf.shop.data;

import java.util.List;
import java.util.Vector;

import org.unibl.etf.shop.entity.Magacioner;
import org.unibl.etf.shop.entity.Trgovac;

public interface IzvjestajiDataAccess {
	Vector<Vector<Object>> zaposleni2020();
	Vector<Vector<Object>> zaposleni2021();
	
	List<Trgovac> trgovciMax(int idTrgovine);
	List<Trgovac> trgovciMin(int idTrgovine);
	List<Magacioner> magacioneriMax(int idMagacina);
	List<Magacioner> magacioneriMin(int idMagacina);
	double minimalnaPlataTrgovca(int idTrgovine);
	double minimalnaPlataMagacionera(int idMagacina);
	double maksimalnaPlataTrgovca(int idTrgovine);
	double maksimalnePlataMagacionera(int idMagacina);
}
