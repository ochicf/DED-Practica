package uoc.ded.practica;

/**
 * Classe que modela una pel.lícula que s'ha pausat.
 * S'indica el minut en el que s'ha pausat la pel.lícula
 * i el munut en el que es va realitzar la pausa
 *
 */
public class PausedMovie {
	private Movie m;
	private int minute;
	
	public PausedMovie(Movie m, int minute) {
		this.m = m; 
		this.minute=minute;
	}
	
	public Movie getMovie() {
		return this.m;
	}
	
	public String toString(String prefix) {
		StringBuffer sb = new StringBuffer(prefix+"movie: ").append(this.m.getIdMovie()+" - ").append(this.m.getTitle()).append(", minute: ").append(this.minute);
		return sb.toString();
	}

	public String toString() {
		// TODO Auto-generated method stub
		return toString("");
	}
	

}
