package uoc.ded.practica;

import java.util.Date;

import uoc.ei.tads.Iterador;

/**
 * Definició del TAD de gestió de continguts multimedia
 */
public interface ContentManager {

	public static final int P = 200000;
	public static final int TOP_MOVIES = 10;

	/**
	 * Mètode que afegeix un usuari al sistema.
	 * 
	 * @post: Si el codi d’usuari és nou, els usuaris seran els mateixos més un
	 *        nou usuari amb les dades indicades. Sinó les dades de l’usuari
	 *        s’hauran actualitzat amb les noves.
	 * 
	 * @param idUser
	 *            identificador de l'usuari
	 * @param name
	 *            nom de l'usuari
	 * @param surname
	 *            cognom de l'usuari
	 */
	public void addUser(String idUser, String name, String surname);

	/**
	 * Mètode que afegeix una pel.lícula al sistema.
	 * 
	 * @pre cert
	 * @post: Les pel·lícules seran les mateixes més una de nova amb les dades
	 *        indicades. Si existeix una pel·lícula amb id idMovie torna error.
	 * 
	 * @param idMovie
	 *            identificador de la pel.lícula
	 * @param title
	 *            títol d'una pel.lícula
	 * @param duration
	 *            temps que dura la pel.lícula
	 * @param director
	 *            director de la pel.lícula
	 * @throws DEDException
	 *             llença una excepció en cas d'error prèviament indicat
	 */
	public void addMovie(String idMovie, String title, int duration, String director) throws DEDException;

	/**
	 * Mètode que indica la visualització d'una pel.lícula per part d'un usuari.
	 * 
	 * @pre: la pel·lícula i l’usuari han d’existir.
	 * @post la pel·lícula que està visualitzant l’usuari passa a ser la
	 *       pel·lícula especificada.
	 * 
	 * @param idUser
	 *            identificador de l'usuari
	 * @param idMovie
	 *            identificador de la pel.lícula
	 */
	public void watchMovie(String idUser, String idMovie);

	/**
	 * Mètode que indica al sistema la finalització d'una pel.lícula per part
	 * d'un usuari
	 * 
	 * @pre cert.
	 * @post les pel·lícules vistes per l’usuari seran els mateixes més una nova
	 *       entrada amb la pel·lícula especificada i la data i hora de
	 *       finalització també especificades. Si la pel·lícula no està sent
	 *       visualitzada per l’usuari retorna un error. L’usuari no estarà
	 *       visualitzant cap pel·lícula.
	 * 
	 * @param idUser
	 *            identificador de l'usuari
	 * @param dateTime
	 *            data en la que es produeix la finalització de la pel.lícula
	 * @throws DEDException
	 *             llença una excepció en cas d'error prèviament indicat
	 */
	public void endMovie(String idUser, Date dateTime) throws DEDException;

	/**
	 * Mètode que indica al sistema la pausa d'una pel.lícula per part d'un
	 * usuari
	 * 
	 * @pre cert.
	 * @post la pel·lícula en pausa per l’usuari passa a ser la pel·lícula
	 *       especificada al minut especificat. L’usuari passa a no estar
	 *       visualitzant cap pel·lícula. Si l’usuari no existeix, no està
	 *       visualitzant cap pel·lícula o el minut es major que la durada de la
	 *       pel·lícula retorna un error.
	 *
	 * @param idUser
	 *            identificador de l'usuari que realitza una pausa de la
	 *            pel.lícula que s'està visualitzant
	 * @param minute
	 *            minut en el que es realitza la pausa
	 * @throws DEDException
	 *             llença una excepció en cas d'error prèviament indicat
	 */
	public void pauseMovie(String idUser, int minute) throws DEDException;

	/**
	 * Mètode que indica al sistema que es torna a visualitzar la pel.lícula que
	 * prèviament s'ha pausat
	 * 
	 * @pre   cierto.
	 * @post  la   película que està visualitzant l'usuari pasa a ser la 
	 *        pel.lícula especificada per idMovie. L'usuari pasa a tenir la en 
	 *        pausa. Si l'usuari no existeix o no té la pel.lícula idMovie en pausa,
	 *        retorna un error.
	 *
	 * @param idUser
	 *            identificador de l'usuari
	 * @param idMovie
	 * 			  identificador de la pel.lícula           
	 *            
	 * @throws DEDException
	 *             llença una excepció en cas d'error prèviament indicat
	 */
	public void resumeMovie(String idUser, String idMovie) throws DEDException;

	/**
	 * Mètode que properciona les visualitzacions d'un usuari per ordre
	 * cronològic
	 * 
	 * @pre existeix un usuari amb identificador idUser
	 * @post retorna un iterador per recórrer les visualitzacions de l'usuari
	 *       per ordre cronològic.
	 * 
	 * @param idUser
	 *            identificador de l'usuari
	 * @return retorna un iterador amb les visualitzacions de l'usuari
	 * @throws DEDException
	 *             llença una excepció en cas d'error prèviament indicat
	 */
	public Iterador<WatchedMovie> getUserWatchedMovies(String idUser) throws DEDException;

	/**
	 * Mètode que retorna un iterador de les 10 pel.lícules més vistes. En cas
	 * que no hi hagi cap pel.lícula es mostrarà un missatge
	 * 
	 * @pre cert
	 * @post retorna un iterador per recórrer les 10 pel·lícules més vistes
	 *       ordenades segons el nombre total de visualitzacions.
	 *
	 * @return retorna un iterador amb les 10 pel.lícules
	 * @throws DEDException
	 *             llença una excepció en cas d'error prèviament indicat
	 */
	public Iterador<Movie> topMovies() throws DEDException;
	
	
	/**
	 * 
	 * @pre l'usuari ha d'existir
	 * @post retorna la visualització feta per un usuari en   una   determinada   
	 *       data (dia i hora). Si no hi ha cap visualització a la data retorna
	 *       un error.
	 * @param idUser
	 * @param date
	 * @return retorna la visualització feta per un usuari en   una   determinada 
	 *         data (dia i hora). Si no hi ha cap visualització a la data retorna 
	 *         un error.
	 */
	public WatchedMovie getWatchedMovie(String idUser, Date date) throws DEDException ;
	
	/**
	 * @pre   la pel.lícula ha d'existir i la puntuació ha de ser un enter 
	 *        entre 0 i 100
	 * @post  Les pel.lícules puntuades son les mateixes més una nova amb la puntuació indicada
	 * @param idMovie identificador de la pel.lícula
	 * @param rating puntuació
	 */
	public void rateMovie(String idMovie, int rating);
	
	
	/**
	 * @pre cert 
	 * @post les peli.lículas puntuades són les mateixes menys les pel.lícules amb més puntuación.   
	 * 		  Si no hi ha cap pel-lícula puntuada retorna un error.
	 * @return retorna la pel.lícula amb més puntuació
	 */
	public Movie bestRated() throws DEDException  ;
	

	/**
	 * Mètode auxiliar per mostrar informació sobre un usuari
	 * 
	 * @param idUser
	 *            identificador de l'usuari
	 * @return retorna l'usuari expecificat
	 * @throws DEDException
	 *             llença una excepció en cas que l'usuari no existeixi
	 */
	public User getUser(String idUser) throws DEDException;

	/**
	 * Mètode auxiliar que proporciona els usuaris en el sistema. En cas que no
	 * hi hagi usuaris es mostrarà un error
	 * 
	 * @return retorna un iterador amb els usuaris del sistema.
	 * @throws DEDException
	 *             llença una excepció en cas que no hi hagi usuaris
	 */
	public Iterador<User> users() throws DEDException;

	/**
	 * Mètode auxiliar que proporciona les pel.lícules del sistema. En cas que
	 * no hi hagi pel.lícules es mostrarà un error
	 * 
	 * @return retorna un iterador amb les pel.lícules
	 * @throws DEDException
	 *             llença una excepció en cas que no hi hagi pel.lícules
	 */
	public Iterador<Movie> movies() throws DEDException;

	
	/**
	 * Mètode auxiliar que proporciona informació sobre una pel.lícula. En cas que
	 * no hi existeixi la pel.lícula  es mostrarà un error
	 * 
	 * @param idMovie
	 *            identificador de la pel.lícula
	 * 
	 * @return retorna una pel.lícula identificada per idMovie
	 * @throws DEDException
	 *             llença una excepció en cas que no hi existeixi la pel.lícula
	 */
	public Movie getMovie(String idMovie) throws DEDException;

}
