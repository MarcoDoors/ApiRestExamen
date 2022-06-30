package com.mx.apiRest.servicio;

import java.util.HashMap;
import com.mx.apiRest.dominio.Record;

public class MethodsImp implements Methods{
	
	HashMap<String, Record> hash = new HashMap<String, Record>();
	Record record;
	
	@Override
	public void guardar(Object obj) {
		record = (Record) obj;
		hash.put(record.getCp(), record);
	}

	@Override
	public void eliminar(Object obj) {
		record = (Record) obj;
		hash.remove(record.getCp());
	}

	@Override
	public Object buscar(Object obj) {
		record = (Record) obj;
		return hash.get(record.getCp());
	}

	@Override
	public void mostrar() {
		System.out.println(hash);
	}
	
	public void eliminarCompleto() {
		hash.clear();
		System.out.println("Se ha eliminado el historial \n"+hash);
	}

}
