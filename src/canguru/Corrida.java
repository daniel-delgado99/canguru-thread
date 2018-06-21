package canguru;

import java.util.ArrayList;

public class Corrida {
	static int tamanhoCorrida=(int) (Math.random()*20)+80;
	static int ordem=0;
	static boolean acabou=false;
	static Object lock = new Object();
	static Canguru[] cangurus = {
			new Canguru("canguru 0", 0),
			new Canguru("canguru 1", 1),
			new Canguru("canguru 2", 2),
			new Canguru("canguru 3", 3),
			new Canguru("canguru 4", 4),
		};
	static ArrayList<Canguru> colocacao = new ArrayList<Canguru>();
	
	public static void main(String[] args) {
		
		System.out.println("Tamanho da corrida: " + tamanhoCorrida);
		System.out.println();
		
        cangurus[0].start();
        cangurus[1].start();
        cangurus[2].start();
        cangurus[3].start();
        cangurus[4].start();
	}
	
	public static void mostraColocação() {
		for (int i=0; i<colocacao.size(); i++) {
			System.out.println(i+1 + "º lugar: " + colocacao.get(i).nome);
			
		}
	}
}
