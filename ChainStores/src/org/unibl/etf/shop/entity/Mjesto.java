package org.unibl.etf.shop.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Mjesto implements Serializable{
	private String BrojPoste;
	private String Naziv;
	
	public Mjesto() {}

	public Mjesto(String brojPoste, String naziv) {
		super();
		BrojPoste = brojPoste;
		Naziv = naziv;
	}

	public String getBrojPoste() {
		return BrojPoste;
	}

	public void setBrojPoste(String brojPoste) {
		BrojPoste = brojPoste;
	}

	public String getNaziv() {
		return Naziv;
	}

	public void setNaziv(String naziv) {
		Naziv = naziv;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BrojPoste == null) ? 0 : BrojPoste.hashCode());
		result = prime * result + ((Naziv == null) ? 0 : Naziv.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mjesto other = (Mjesto) obj;
		if (BrojPoste == null) {
			if (other.BrojPoste != null)
				return false;
		} else if (!BrojPoste.equals(other.BrojPoste))
			return false;
		if (Naziv == null) {
			if (other.Naziv != null)
				return false;
		} else if (!Naziv.equals(other.Naziv))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  BrojPoste + ", " + Naziv ;
	}

	
	
	

}
