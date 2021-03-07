package org.unibl.etf.shop.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProizvodUOrganizacionojJedinici  implements Serializable{
	private Proizvod proizvod;
	private OrganizacionaJedinica oj;
	private double kolicina;
	
	public ProizvodUOrganizacionojJedinici() {}

	public ProizvodUOrganizacionojJedinici(Proizvod proizvod, OrganizacionaJedinica oj, double kolicina) {
		super();
		this.proizvod = proizvod;
		this.oj = oj;
		this.kolicina = kolicina;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public OrganizacionaJedinica getOj() {
		return oj;
	}

	public void setOj(OrganizacionaJedinica oj) {
		this.oj = oj;
	}

	public double getKocina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(kolicina);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((oj == null) ? 0 : oj.hashCode());
		result = prime * result + ((proizvod == null) ? 0 : proizvod.hashCode());
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
		ProizvodUOrganizacionojJedinici other = (ProizvodUOrganizacionojJedinici) obj;
		if (Double.doubleToLongBits(kolicina) != Double.doubleToLongBits(other.kolicina))
			return false;
		if (oj == null) {
			if (other.oj != null)
				return false;
		} else if (!oj.equals(other.oj))
			return false;
		if (proizvod == null) {
			if (other.proizvod != null)
				return false;
		} else if (!proizvod.equals(other.proizvod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProizvodUOrganizacionojJedinici [proizvod=" + proizvod + ", oj=" + oj + ", kolicina=" + kolicina + "]";
	}
	
	

}
