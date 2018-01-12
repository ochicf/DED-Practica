package uoc.ded.practica;

import java.util.Comparator;

import uoc.ei.tads.ClauValor;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.Llista;
import uoc.ei.tads.LlistaEncadenada;
import uoc.ei.tads.Posicio;
import uoc.ei.tads.Recorregut;

/**
 * Classe que modela un usuari
 *
 */
public class User {
	
	private String idUser;
	private String name;
	private String surname;
	
	private Llista<PausedMovie> pausedMovies;
	
	private Movie watchingMovie;
	private LlistaEncadenadaOrdenada<WatchedMovie> watchedMovies;
	
	public static Comparator<String> CMP = new Comparator<String>() {

		@Override
		public int compare(String idUser1, String idUser2) {
			int ret = idUser1.compareTo(idUser2);
			return ret;
		}
	};
	
	public static Comparator<ClauValor<String, User>> CMP_CLAU_VALOR = new Comparator<ClauValor<String, User>>() {

		@Override
		public int compare(ClauValor<String, User> cvUser1, ClauValor<String, User> cvUser2) {
			int ret = cvUser1.compareTo(cvUser2);
			return ret;
		}
	};
	
	public User(String idUser, String name, String surname) {
		this.idUser=idUser;
		this.name = name;
		this.surname = surname;
		this.watchingMovie=null;
		this.pausedMovies = new LlistaEncadenada<PausedMovie>();
		this.watchedMovies = new LlistaEncadenadaOrdenada<WatchedMovie>(WatchedMovie.CMP);
	}

	public String getIdUser() {
		return this.idUser;
	}

	public boolean isWatchingMovie() {
		return (this.watchingMovie!=null);
	}

	public Movie watchingMovie() {
		return this.watchingMovie;
	}

	public void addWatchedMovie(WatchedMovie wm) {
		this.watchedMovies.add(wm);
	}

	public PausedMovie pauseMovie(int minute) throws DEDException {
		PausedMovie pm = null;
		Movie m = this.watchingMovie();
		if (minute > m.getDuration()) throw new DEDException(Messages.MOVIE_DURATION_EXCEEDED);
		else {		
			pm = new PausedMovie(m, minute);
			this.pausedMovie = pm;
			this.watchingMovie = null;
		}
		return pm;
	}

	public PausedMovie pausedMovie() {
		return this.pausedMovie;
	}

	/**
	 * Looks for a movie in the user's paused movies and returns its position.
	 * I've added this private method to apply DRY.
	 * 
	 * @param idMovie
	 * @return position of the paused movie
	 */
	private Posicio<PausedMovie> posicioPausedMovie(String idMovie) {
		Recorregut<PausedMovie> pausedMovies = this.pausedMovies.posicions();
		while (pausedMovies.hiHaSeguent()) {
			Posicio<PausedMovie> posicio = pausedMovies.seguent();
			Movie movie = posicio.getElem().getMovie();
			if (movie.getIdMovie().equals(idMovie)) {
				return posicio;
			}
		}
		return null;
	}
	
	public void setWatchingMovie(Movie movie) {
		this.watchingMovie=movie;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("id: ").append(this.idUser).append(" ");
		sb.append("name: ").append(this.name).append(" ");
		sb.append("surname: ").append(this.surname).append(" ");
		if (this.pausedMovie!=null) sb.append(Messages.LS+"paused movie: "+Messages.LS).append(this.pausedMovie.toString("\t")).append(Messages.LS);
		if (this.isWatchingMovie()) sb.append(Messages.LS+"watching movie: "+Messages.LS).append(this.watchingMovie.toString("\t")).append(Messages.LS);
		
		return sb.toString();
	}

	public void resumeMovie() {
		this.pausedMovie=null;
	}

	public void endMovie() {
		this.watchingMovie=null;
	}

	public LlistaEncadenadaOrdenada<WatchedMovie> getWatchedMovies() {
		return this.watchedMovies;		
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public void setSurname(String surname) {
		this.surname=surname;
	}

}
