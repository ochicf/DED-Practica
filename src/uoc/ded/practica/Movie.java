package uoc.ded.practica;

import java.util.Comparator;

/**
 * Classe que modela una entitat d'informació pel.lícula
 *
 */
public class Movie implements Comparable<Movie>{

	private String idMovie;
	private String title;
	private int duration;
	private String director;
	private int views;
	private int rating;

	public static final Comparator<Movie> CMP =  new Comparator<Movie>() {

		@Override
		public int compare(Movie m1, Movie m2) {
			return m1.views()- m2.views();
		}
	};

	/**
	 * Descending rating comparator.
	 */
	public static final Comparator<Movie> CMP_RATING =  new Comparator<Movie>() {

		@Override
		public int compare(Movie m1, Movie m2) {
			return m2.rating - m1.rating;
		}
	};

	public Movie(String idMovie, String title, int duration, String director) {
		this.idMovie=idMovie;
		this.title = title;
		this.duration=duration;
		this.director = director;
		this.rating = 0;
	}
	
	public int views() {
		return this.views;
	}
	
	public void incViews() {
		this.views++;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String toString() {
		return toString("");
		
	}

	public String toString(String prefix) {
		StringBuffer sb = new StringBuffer(prefix).append("idMovie:").append(this.idMovie).append(Messages.LS);
		sb.append(prefix).append("title: ").append(this.title).append(Messages.LS);
		sb.append(prefix).append("director: ").append(this.director).append(Messages.LS);
		sb.append(prefix).append("duration: ").append(this.duration).append(Messages.LS);
		sb.append(prefix).append("views: ").append(this.views()).append(Messages.LS);
		sb.append(prefix).append("rating: ").append(this.rating).append(Messages.LS);
		
		return sb.toString();
	}
	
	public int compareTo(Movie m) {
		return this.idMovie.compareTo(m.idMovie);
	}

	public String getIdMovie() {
		return this.idMovie;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public int getRating() {
		return this.rating;
	}

}
