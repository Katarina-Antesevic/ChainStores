package org.unibl.etf.shop.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Trgovac  implements Serializable{
	private Prodavnica prodavnica;
	private Zaposleni zaposleni;
	
	public Trgovac() {}

	public Trgovac(Prodavnica prodavnica, Zaposleni zaposleni) {
		super();
		this.prodavnica = prodavnica;
		this.zaposleni = zaposleni;
	}

	public Prodavnica getProdavnica() {
		return prodavnica;
	}

	public void setProdavnica(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
	}

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodavnica == null) ? 0 : prodavnica.hashCode());
		result = prime * result + ((zaposleni == null) ? 0 : zaposleni.hashCode());
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
		Trgovac other = (Trgovac) obj;
		if (prodavnica == null) {
			if (other.prodavnica != null)
				return false;
		} else if (!prodavnica.equals(other.prodavnica))
			return false;
		if (zaposleni == null) {
			if (other.zaposleni != null)
				return false;
		} else if (!zaposleni.equals(other.zaposleni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JMB:" +zaposleni.getJmb()+"    "+zaposleni.getIme()+" "+zaposleni.getPrezime();
	}
	
	

}
