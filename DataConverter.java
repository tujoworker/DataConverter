/**
 * Main class of the DataConverter
 *
 * @author Tobias Høegh <tobias@tujo.no>
 */

//import the DataHandler class
import com.dataconverter.DataHandler;

//main class
public class DataConverter {

//the main class gets startet by running $ java -classpath build DataConverter
public static void main(String argv[]) {

        //some conifg
        String APIURL = "http://sumo.tv2.no/api/web/search/categories/6676672/assets?start=0&size=10&pagination=false";
        String APISUBURL = "http://sumo.tv2.no/rest/assets/<id>/related";
        String OUTPUT = "API.json";

        //run the app
        String json = new DataHandler().run(APIURL, APISUBURL, OUTPUT);

        //if needed, then print out the result
        // System.out.println(json);

}

}
