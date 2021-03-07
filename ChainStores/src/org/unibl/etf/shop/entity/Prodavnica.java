package org.unibl.etf.shop.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Prodavnica  implements Serializable{
	private OrganizacionaJedinica organizacionaJedinica;
	private String naziv;
	
	public Prodavnica() {}

	public Prodavnica(OrganizacionaJedinica organizacionaJedinica, String naziv) {
		super();
		this.organizacionaJedinica = organizacionaJedinica;
		this.naziv = naziv;
	}

	public OrganizacionaJedinica getOrganizacionaJedinica() {
		return organizacionaJedinica;
	}

	public void setOrganizacionaJedinica(OrganizacionaJedinica organizacionaJedinica) {
		this.organizacionaJedinica = organizacionaJedinica;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((organizacionaJedinica == null) ? 0 : organizacionaJedinica.hashCode());
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
		Prodavnica other = (Prodavnica) obj;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (organizacionaJedinica == null) {
			if (other.organizacionaJedinica != null)
				return false;
		} else if (!organizacionaJedinica.equals(other.organizacionaJedinica))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return naziv;
	}
	
	

}
