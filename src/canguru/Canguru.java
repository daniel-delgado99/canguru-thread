package canguru;

public class Canguru extends Thread{
	public int id;
	public String nome;
	public boolean chegou=false;
	public int distanciaPercorrida;
	
	public Canguru(String nome, int id){
		super(nome);
		this.id=id;
		this.nome=nome;
    }
	
	public synchronized void pula() {
		int distanciaPulo = (int) (Math.random()*10)+1;
		this.distanciaPercorrida = this.distanciaPercorrida + distanciaPulo;
		System.out.println(this.nome + " pulou " + distanciaPulo);
		System.out.println(this.nome + " percorreu " + this.distanciaPercorrida);
		if (this.distanciaPercorrida >= Corrida.tamanhoCorrida) {
			System.out.println(this.nome + " chegou!");
			Corrida.colocacao.add((Canguru) this);
			this.chegou = true;
		}
		System.out.println();
		
	}
	
	public void run() {
		synchronized(Corrida.lock) {
			while (!this.chegou && !Corrida.acabou) {
				while (Corrida.ordem != this.id) {
					try {
	                    Corrida.lock.wait();
	                } catch (InterruptedException e) {

	                } catch (Exception e) {}
				}
	            
	            this.pula();
	            Corrida.lock.notifyAll();
	            int aux=0;
	            do {
	            	Corrida.ordem++;
	            	if (Corrida.ordem == Corrida.cangurus.length) {
		            	Corrida.ordem = 0;
		            }
	            	aux++;
	            	
	            	if (aux == Corrida.cangurus.length*10) {
	            		Corrida.acabou = true;
	            		Corrida.mostraColocação();
	            		break;	            		
	            	}
	            } while (Corrida.cangurus[Corrida.ordem].chegou);
			}
		}
	}
}