package org.unibl.etf.shop.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dobavljac  implements Serializable{
	private String jib;
	private String Naziv;
	private String Email;
	private String Telefon;
	private String Adresa;
	private Mjesto MJESTO_BrojPoste;
	
	public Dobavljac() {}

	public Dobavljac(String jib, String naziv, String email, String telefon, String adresa, Mjesto mJESTO_BrojPoste) {
		super();
		this.jib = jib;
		Naziv = naziv;
		Email = email;
		Telefon = telefon;
		Adresa = adresa;
		MJESTO_BrojPoste = mJESTO_BrojPoste;
	}

	public String getJib() {
		return jib;
	}

	public void setJib(String jib) {
		this.jib = jib;
	}

	public String getNaziv() {
		return Naziv;
	}

	public void setNaziv(String naziv) {
		Naziv = naziv;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTelefon() {
		return Telefon;
	}

	public void setTelefon(String telefon) {
		Telefon = telefon;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		Adresa = adresa;
	}

	public Mjesto getMJESTO_BrojPoste() {
		return MJESTO_BrojPoste;
	}

	public void setMJESTO_BrojPoste(Mjesto mJESTO_BrojPoste) {
		MJESTO_BrojPoste = mJESTO_BrojPoste;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Adresa == null) ? 0 : Adresa.hashCode());
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((MJESTO_BrojPoste == null) ? 0 : MJESTO_BrojPoste.hashCode());
		result = prime * result + ((Naziv == null) ? 0 : Naziv.hashCode());
		result = prime * result + ((Telefon == null) ? 0 : Telefon.hashCode());
		result = prime * result + ((jib == null) ? 0 : jib.hashCode());
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
		Dobavljac other = (Dobavljac) obj;
		if (Adresa == null) {
			if (other.Adresa != null)
				return false;
		} else if (!Adresa.equals(other.Adresa))
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (MJESTO_BrojPoste == null) {
			if (other.MJESTO_BrojPoste != null)
				return false;
		} else if (!MJESTO_BrojPoste.equals(other.MJESTO_BrojPoste))
			return false;
		if (Naziv == null) {
			if (other.Naziv != null)
				return false;
		} else if (!Naziv.equals(other.Naziv))
			return false;
		if (Telefon == null) {
			if (other.Telefon != null)
				return false;
		} else if (!Telefon.equals(other.Telefon))
			return false;
		if (jib == null) {
			if (other.jib != null)
				return false;
		} else if (!jib.equals(other.jib))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Naziv ;
	}

	
	
	

}
