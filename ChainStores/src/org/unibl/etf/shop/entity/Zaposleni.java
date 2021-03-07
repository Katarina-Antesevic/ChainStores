package org.unibl.etf.shop.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Zaposleni  implements Serializable{
	private String JMB_Zaposlenog;
	private String Ime;
	private String Prezime;
	private String Email;
	private String Telefon;
	private double Plata;
	private Date DatumOd;
	private String Adresa;
	private Mjesto MJESTO_BrojPoste;
	
	public Zaposleni() {}

	public Zaposleni(String JMB_Zaposlenog, String ime, String prezime, String email, String telefon, double plata,
			String adresa, Mjesto mJESTO_BrojPoste) {
		super();
		this.JMB_Zaposlenog = JMB_Zaposlenog;
		Ime = ime;
		Prezime = prezime;
		Email = email;
		Telefon = telefon;
		Plata = plata;
		DatumOd = new Date();
		Adresa = adresa;
		MJESTO_BrojPoste = mJESTO_BrojPoste;
	}
	
	

	public Zaposleni(String jMB_Zaposlenog, String ime, String prezime, String email, String telefon, double plata,
			Date datumOd, String adresa, Mjesto mJESTO_BrojPoste) {
		super();
		JMB_Zaposlenog = jMB_Zaposlenog;
		Ime = ime;
		Prezime = prezime;
		Email = email;
		Telefon = telefon;
		Plata = plata;
		DatumOd = datumOd;
		Adresa = adresa;
		MJESTO_BrojPoste = mJESTO_BrojPoste;
	}

	public String getJmb() {
		return JMB_Zaposlenog;
	}

	public void setJmb(String JMB_Zaposlenog) {
		this.JMB_Zaposlenog = JMB_Zaposlenog;
	}

	public String getIme() {
		return Ime;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getPrezime() {
		return Prezime;
	}

	public void setPrezime(String prezime) {
		Prezime = prezime;
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

	public double getPlata() {
		return Plata;
	}

	public void setPlata(double plata) {
		Plata = plata;
	}

	public Date getDatumOd() {
		return DatumOd;
	}

	public void setDatumOd() {
		DatumOd = new Date();
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
		result = prime * result + ((DatumOd == null) ? 0 : DatumOd.hashCode());
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((Ime == null) ? 0 : Ime.hashCode());
		result = prime * result + ((JMB_Zaposlenog == null) ? 0 : JMB_Zaposlenog.hashCode());
		result = prime * result + ((MJESTO_BrojPoste == null) ? 0 : MJESTO_BrojPoste.hashCode());
		long temp;
		temp = Double.doubleToLongBits(Plata);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((Prezime == null) ? 0 : Prezime.hashCode());
		result = prime * result + ((Telefon == null) ? 0 : Telefon.hashCode());
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
		Zaposleni other = (Zaposleni) obj;
		if (Adresa == null) {
			if (other.Adresa != null)
				return false;
		} else if (!Adresa.equals(other.Adresa))
			return false;
		if (DatumOd == null) {
			if (other.DatumOd != null)
				return false;
		} else if (!DatumOd.equals(other.DatumOd))
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (Ime == null) {
			if (other.Ime != null)
				return false;
		} else if (!Ime.equals(other.Ime))
			return false;
		if (JMB_Zaposlenog == null) {
			if (other.JMB_Zaposlenog != null)
				return false;
		} else if (!JMB_Zaposlenog.equals(other.JMB_Zaposlenog))
			return false;
		if (MJESTO_BrojPoste == null) {
			if (other.MJESTO_BrojPoste != null)
				return false;
		} else if (!MJESTO_BrojPoste.equals(other.MJESTO_BrojPoste))
			return false;
		if (Double.doubleToLongBits(Plata) != Double.doubleToLongBits(other.Plata))
			return false;
		if (Prezime == null) {
			if (other.Prezime != null)
				return false;
		} else if (!Prezime.equals(other.Prezime))
			return false;
		if (Telefon == null) {
			if (other.Telefon != null)
				return false;
		} else if (!Telefon.equals(other.Telefon))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JMB:"+JMB_Zaposlenog + "    " + Ime + " " + Prezime;
	}
	
	
	
	
	

}
