package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import persistence.hibernate.hibernateDAO.CasosHibernateDAO;
import persistence.util.Coordinates;

import beans.Caso;

public class Povoar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CasosHibernateDAO casosHibernate = new CasosHibernateDAO();
		try {
			Scanner scan = new Scanner(new File("povoar.txt"));
			int qte = Integer.parseInt(scan.nextLine());
			for (int i = 0; i < qte; i++) {
				Caso caso = new Caso();
				caso.setIdAdministradorResponsavel(Long.parseLong(scan.nextLine()));
				caso.setCidade(scan.nextLine());
				caso.setEstado(scan.nextLine());
				caso.setBairro(scan.nextLine());
				caso.setRua(scan.nextLine());
				caso.setNumero(Integer.parseInt(scan.nextLine()));
				caso.setNome(scan.nextLine());
				caso.setAreaConstruida(Float.parseFloat(scan.nextLine()));
				caso.setAreaTotal(Float.parseFloat(scan.nextLine()));
				caso.setVagasGaragem(Integer.parseInt(scan.nextLine()));
				caso.setQuartos(Integer.parseInt(scan.nextLine()));
				caso.setSuites(Integer.parseInt(scan.nextLine()));
				caso.setBanheiros(Integer.parseInt(scan.nextLine()));
				caso.setTipo(scan.nextLine());
				caso.setPreco(Float.parseFloat(scan.nextLine()));
				caso.setTipoNegocio(scan.nextLine());
				caso.setObservacoes(scan.nextLine());
				double latitude = Double.parseDouble(scan.nextLine());
				double longitude = Double.parseDouble(scan.nextLine());
				caso.setLocation(new Coordinates(latitude, longitude));
				casosHibernate.saveCaso(caso);
				scan.nextLine();
				System.out.println("Base de dados povoada com sucesso");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
