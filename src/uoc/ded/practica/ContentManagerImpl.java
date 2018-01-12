package uoc.ded.practica;


import java.util.Date;

import uoc.ei.tads.Diccionari;
import uoc.ei.tads.DiccionariVectorImpl;
import uoc.ei.tads.Iterador;

public class ContentManagerImpl implements ContentManager {
	
	DiccionariOrderedVector<String, User> users;
	Diccionari<String, Movie> movies;
	
	OrderedVector<Movie> topMovies;
	
	public ContentManagerImpl() {
		this.users = new DiccionariOrderedVector<String, User> (U, User.CMP);
		this.movies = new DiccionariVectorImpl<String, Movie>(P);
		this.topMovies=new OrderedVector<Movie>(TOP_MOVIES, Movie.CMP);
	}

	
	@Override
	public void addUser(String idUser, String name, String surname) {
		
		User user = this.users.consultar(idUser);
		if (user == null) {
			this.users.afegir(idUser, new User(idUser, name, surname));
		}
		else {
			user.setName(name);
			user.setSurname(surname);
		}
		
	}

	@Override
	public void addMovie(String idMovie, String title, int duration, String director) throws DEDException {
		Movie m = this.movies.consultar(idMovie);
		if (m!=null) throw new DEDException(Messages.MOVIES_ALREADY_EXIST);
		
		m = new Movie(idMovie, title, duration, director);
		
		this.movies.afegir(idMovie, m);
	}

	@Override
	public void watchMovie(String idUser, String idMovie)  {

		User u = this.users.consultar(idUser);
		// @pre
		//if (u==null) ...
		
		
		Movie m = this.movies.consultar(idMovie);
		
		// @pre 
		//if (m==null) throw new DEDException (Messages.MOVIE_NOT_FOUND);
		
		u.setWatchingMovie(m);
		
	}

	@Override
	public void endMovie(String idUser, Date dateTime) throws DEDException {
		User u = this.getUser(idUser);
		if (u.isWatchingMovie()) {
			Movie m = u.watchingMovie();
			m.incViews();
			WatchedMovie wm = new WatchedMovie(m, dateTime);
			u.addWatchedMovie(wm);
			u.endMovie();
			this.topMovies.update(m);
		}
		else throw new DEDException(Messages.USER_WATCHING_NO_MOVIES);
	}

	@Override
	public void pauseMovie(String idUser, int minute) throws DEDException {
		PausedMovie pm = null;
		User u = this.getUser(idUser);
		if (u.isWatchingMovie()) {
			pm = u.pauseMovie(minute);	
		}
		else throw new DEDException(Messages.USER_WATCHING_NO_MOVIES);
		
	}

	@Override
	public void resumeMovie(String idUser) throws DEDException {
		User u = this.getUser(idUser);
		PausedMovie pm = u.pausedMovie();
		if (pm==null) throw new DEDException(Messages.NO_PAUSED_MOVIE);
		
		u.setWatchingMovie(pm.getMovie());	
		u.resumeMovie();
	}

	
	@Override
	public Iterador<WatchedMovie> getUserWatchedMovies(String idUser) throws DEDException {
		User u = this.users.consultar(idUser);
		
		// @pre
		// if (u == null) ...
		
		LlistaEncadenadaOrdenada<WatchedMovie> ll = u.getWatchedMovies();	
		if (ll.estaBuit()) throw new DEDException(Messages.NO_WATCHED_MOVIES);
		
		
		return (ll.elements());
	}


	@Override
	public Iterador<Movie> topMovies() throws DEDException {
		if (this.topMovies.estaBuit()) throw new DEDException(Messages.NO_MOVIES);
		return this.topMovies.elements();
	}
	
	public User getUser(String idUser) throws DEDException {
		User u = this.users.consultar(idUser);
		if (u==null) throw new DEDException(Messages.USER_NOT_FOUND);
		return u;
	}


	@Override
	public Iterador<User> users() throws DEDException {
		if (this.users.estaBuit()) throw new DEDException(Messages.NO_USERS);
		return this.users.elements();
	}


	@Override
	public Iterador<Movie> movies() throws DEDException {
		if (this.movies.estaBuit()) throw new DEDException(Messages.NO_MOVIES);
		return this.movies.elements();
	}




    
}