package org.unibl.etf.shop.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrganizacionaJedinica  implements Serializable{
	private int Id_OrganizacioneJedinice;
	private String Email;
	private String Adresa;
	private Mjesto MJESTO_BrojPoste;

	public OrganizacionaJedinica() {}

	public OrganizacionaJedinica(int id_OrganizacioneJedinice, String email, String adresa, Mjesto mJESTO_BrojPoste) {
		super();
		Id_OrganizacioneJedinice = id_OrganizacioneJedinice;
		Email = email;
		Adresa = adresa;
		MJESTO_BrojPoste = mJESTO_BrojPoste;
	}

	public int getId_OrganizacioneJedinice() {
		return Id_OrganizacioneJedinice;
	}

	public void setId_OrganizacioneJedinice(int id_OrganizacioneJedinice) {
		Id_OrganizacioneJedinice = id_OrganizacioneJedinice;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
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
		result = prime * result + Id_OrganizacioneJedinice;
		result = prime * result + ((MJESTO_BrojPoste == null) ? 0 : MJESTO_BrojPoste.hashCode());
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
		OrganizacionaJedinica other = (OrganizacionaJedinica) obj;
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
		if (Id_OrganizacioneJedinice != other.Id_OrganizacioneJedinice)
			return false;
		if (MJESTO_BrojPoste == null) {
			if (other.MJESTO_BrojPoste != null)
				return false;
		} else if (!MJESTO_BrojPoste.equals(other.MJESTO_BrojPoste))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Organizaciona jedinica " + Id_OrganizacioneJedinice ;
	}
	
	
	
	
}
