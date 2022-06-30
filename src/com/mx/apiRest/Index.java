package com.mx.apiRest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mx.apiRest.servicio.MethodsImp;
import com.mx.apiRest.dominio.Record;

public class Index {

	public static void main(String[] args) {
		
		String cp, pais ="", estado ="", ciudad ="", municipio ="", tipo_asentamiento ="", asentamiento ="", fecha ="";
		DateFormat dateF;
		char acept = ' ';
		int menu=0;
		
		MethodsImp mImp = new MethodsImp();
		Record record = null;
		Scanner scan;
		
		System.out.println("                 BIENVENIDO");
		System.out.println("       Por Favor digite la opcion desee");
		
		do {
			try {
				System.out.println("\n----------------------------------------------");
				System.out.println("|              MENU DE OPCIONES              |");
				System.out.println("----------------------------------------------");
				System.out.println("| 1.-   Consultar codigo postal              |");
				System.out.println("| 2.-   Historial de consultas               |");
				System.out.println("| 3.-   Eliminar un registro del historial   |");
				System.out.println("| 4.-   Eliminar historial                   |");
				System.out.println("----------------------------------------------");
				System.out.println("| 5.-   Salir                                |");
				System.out.println("----------------------------------------------");
				
				scan = new Scanner(System.in);
				menu = scan.nextInt();
				
				switch (menu) {
				case 1:
					System.out.println("\n---------------------------------------------");
					System.out.println("|          Consultar Codigo Postal          |");
					System.out.println("---------------------------------------------");
					System.out.println("     Digite el Codigo Postal de la region");
					System.out.println("             que desea consultar");
					scan = new Scanner(System.in);
					cp = scan.nextLine();
					
					try {
						String endpoint_sepomex = "https://api.copomex.com/query/info_cp/";
						String codPos = cp;
						String token = "?token=";
						String user = "f0acf64a-3167-4b6f-b20b-92d37c2be3d4";
						String url_sepomex = endpoint_sepomex + codPos + token + user;
						//https://api.copomex.com/query/info_cp/90807?token=f0acf64a-3167-4b6f-b20b-92d37c2be3d4
						URL url = new URL(url_sepomex);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.connect();
	
						int responseCode = conn.getResponseCode();
						if (responseCode != 200) {
							throw new RuntimeException("\n Error en codigo postal: " + responseCode);
						} else {
							StringBuilder informationString = new StringBuilder();
							scan = new Scanner(url.openStream());
							while (scan.hasNext()) {
								informationString.append(scan.nextLine());
							}
							scan.close();
							JSONArray arregloJson = new JSONArray(informationString.toString());
							JSONObject jsonObject = arregloJson.getJSONObject(0);
							JSONObject respuesta = jsonObject.getJSONObject("response");
	
							cp = respuesta.getString("cp");
							pais = respuesta.getString("pais");
							estado = respuesta.getString("estado");
							ciudad = respuesta.getString("ciudad");
							municipio = respuesta.getString("municipio");
							asentamiento = respuesta.getString("asentamiento");
							tipo_asentamiento = respuesta.getString("tipo_asentamiento");
	
							System.out.println("\nCodigo Postal: " + cp);
							System.out.println("Pais: " + pais);
							System.out.println("Estado: " + estado);
							System.out.println("Ciudad: " + ciudad);
							System.out.println("Municipio: " + municipio);
							System.out.println("Asentamiento: " + asentamiento);
							System.out.println("Tipo de asentamiento: " + tipo_asentamiento);
						}
					} catch (Exception e) {
						System.out.println("\n Error:" + e);
					}
					dateF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date date = new Date();
					fecha = dateF.format(date);
					record = new Record(cp, pais, estado, ciudad, municipio, tipo_asentamiento,asentamiento, fecha);
					mImp.guardar(record);
					break;
				case 2:
					System.out.println("\n---------------------------------------------");
					System.out.println("|             Mostrar Registros             |");
					System.out.println("---------------------------------------------");
					mImp.mostrar();
					break;
				case 3:
					System.out.println("\n---------------------------------------------");
					System.out.println("|    Eliminar un Registro del Historial     |");
					System.out.println("---------------------------------------------");
					mImp.mostrar();
					System.out.println("\n          Digite el Codigo Postal");
					scan = new Scanner(System.in);
					cp = scan.nextLine();
					record = new Record(cp);
					record=(Record) mImp.buscar(record);
					
					System.out.println("\n      Si desea aceptar digite  * s * ");
					scan = new Scanner(System.in);
					acept = scan.nextLine().charAt(0);
					if(acept == 's') {
						mImp.eliminar(record);
						System.out.println("\n El registro "+record+" se elimino con exito!");
					}else {
						System.out.println("Error");
					}
					break;
				case 4:
					System.out.println("\n---------------------------------------------");
					System.out.println("|             Eliminar Historial            |");
					System.out.println("---------------------------------------------");
					mImp.mostrar();
					System.out.println("\n       Desea eliminar el historial?");
					System.out.println("\n      Si desea aceptar digite  * s * ");
					scan = new Scanner(System.in);
					acept = scan.nextLine().charAt(0);
					if(acept == 's') {
						mImp.eliminarCompleto();
						System.out.println("Se eliminaron todos los registros con éxito");
					}else {
						System.out.println("Error");
					}
					break;
				case 5:
					System.out.println("Vuelva Pronto...");
					break;
				}
			}catch (Exception e) {
				System.out.println("Fail"+e);
			}
		}while(menu !=5);
		
	}

}
