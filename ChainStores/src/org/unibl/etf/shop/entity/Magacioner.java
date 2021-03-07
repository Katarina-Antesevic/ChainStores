package org.unibl.etf.shop.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Magacioner  implements Serializable {
	private Magacin magacin;
	private Zaposleni zaposleni;
	
	public Magacioner() {}

	public Magacioner(Magacin magacin, Zaposleni zaposleni) {
		super();
		this.magacin = magacin;
		this.zaposleni = zaposleni;
	}

	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
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
		result = prime * result + ((magacin == null) ? 0 : magacin.hashCode());
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
		Magacioner other = (Magacioner) obj;
		if (magacin == null) {
			if (other.magacin != null)
				return false;
		} else if (!magacin.equals(other.magacin))
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
		return "JMB:" + zaposleni.getJmb() + "    "+ zaposleni.getIme()+" "+zaposleni.getPrezime();
	}
	
	

}
