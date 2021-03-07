package org.unibl.etf.shop.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Proizvod  implements Serializable{
	private String barkod;
	private String Naziv;
	private double Cijena;
	private Proizvodjac proizvodjac;
	private Dobavljac dobavljac;
	
	
	public Proizvod() {}

	public Proizvod(String barkod,  String naziv, double cijena, Proizvodjac proizvodjac, Dobavljac dobavljac) {
		super();
		this.barkod = barkod;
		this.proizvodjac = proizvodjac;
		this.dobavljac = dobavljac;
		Naziv = naziv;
		Cijena = cijena;
	}

	public String getBarkod() {
		return barkod;
	}

	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}

	public Proizvodjac getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public Dobavljac getDobavljac() {
		return dobavljac;
	}

	public void setDobavljac(Dobavljac dobavljac) {
		this.dobavljac = dobavljac;
	}

	public String getNaziv() {
		return Naziv;
	}

	public void setNaziv(String naziv) {
		Naziv = naziv;
	}

	public double getCijena() {
		return Cijena;
	}

	public void setCijena(double cijena) {
		Cijena = cijena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Cijena);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((Naziv == null) ? 0 : Naziv.hashCode());
		result = prime * result + ((barkod == null) ? 0 : barkod.hashCode());
		result = prime * result + ((dobavljac == null) ? 0 : dobavljac.hashCode());
		result = prime * result + ((proizvodjac == null) ? 0 : proizvodjac.hashCode());
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
		Proizvod other = (Proizvod) obj;
		if (Double.doubleToLongBits(Cijena) != Double.doubleToLongBits(other.Cijena))
			return false;
		if (Naziv == null) {
			if (other.Naziv != null)
				return false;
		} else if (!Naziv.equals(other.Naziv))
			return false;
		if (barkod == null) {
			if (other.barkod != null)
				return false;
		} else if (!barkod.equals(other.barkod))
			return false;
		if (dobavljac == null) {
			if (other.dobavljac != null)
				return false;
		} else if (!dobavljac.equals(other.dobavljac))
			return false;
		if (proizvodjac == null) {
			if (other.proizvodjac != null)
				return false;
		} else if (!proizvodjac.equals(other.proizvodjac))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  "Barkod: " +barkod+"       "+Naziv ;
	}

	
	
	
	
}
