package com.mx.apiRest.dominio;

public class Record {
	
	String cp, pais, estado, ciudad, municipio, tipo_asentamiento, asentamiento, fecha;

	public Record() {
	}
	
	

	public Record(String cp) {
		this.cp = cp;
	}



	public Record(String cp, String pais, String estado, String ciudad, String municipio, String tipo_asentamiento,
			String asentamiento, String fecha) {
		this.cp = cp;
		this.pais = pais;
		this.estado = estado;
		this.ciudad = ciudad;
		this.municipio = municipio;
		this.tipo_asentamiento = tipo_asentamiento;
		this.asentamiento = asentamiento;
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Record [cp=" + cp + ", pais=" + pais + ", estado=" + estado + ", ciudad=" + ciudad + ", municipio="
				+ municipio + ", tipo_asentamiento=" + tipo_asentamiento + ", asentamiento=" + asentamiento + ", fecha="
				+ fecha + "]\n";
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getTipo_asentamiento() {
		return tipo_asentamiento;
	}

	public void setTipo_asentamiento(String tipo_asentamiento) {
		this.tipo_asentamiento = tipo_asentamiento;
	}

	public String getAsentamiento() {
		return asentamiento;
	}

	public void setAsentamiento(String asentamiento) {
		this.asentamiento = asentamiento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
