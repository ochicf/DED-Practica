package uoc.ded.practica;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Classe que modela una pel.lícula que s'està visualitzant. 
 * Es guarda la referència de la pel.lícula i la data
 *
 */
public class WatchedMovie {
	public static final Comparator<WatchedMovie> CMP = new Comparator<WatchedMovie>() {

		@Override
		public int compare(WatchedMovie wm1, WatchedMovie wm2) {
			return wm2.date.compareTo(wm1.date);
		}
	};
	
	private Movie m;
	private Date date;

	public WatchedMovie(Movie currentMovie, Date dateTime) {
		this.m=currentMovie;
		this.date=dateTime;
	}
	
	public Movie getMovie() {
		return this.m;
	}
	
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		StringBuffer sb = new StringBuffer(this.m.getIdMovie()+" ").append(m.getTitle()).append(" ").append(df.format(this.date));
		
		return sb.toString();
	}

}
