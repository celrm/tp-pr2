package p1;

public class PeashooterList {
	private Peashooter[] lista;
	private int cont;
	
	public PeashooterList(){
		this.lista = new Peashooter[32] ;
		this.cont = 0;
	}
	
	public int length(){
		return this.cont;
	}
	
	public Peashooter lista(int pos){
		return this.lista[pos];
	}
	
	public void addPeashooter (int x, int y, Game juego){
		this.lista[cont] = new Peashooter(x,y, juego);
		++this.cont;
	}
	
	public void danar(int x, int y, int cant){
		for (int i = 0; i < this.cont; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y){
				this.lista[i].serDanado(cant);
			}
		}
	}
	
}
