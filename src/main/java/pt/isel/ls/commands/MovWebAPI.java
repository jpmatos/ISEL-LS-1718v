package pt.isel.ls.commands;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.mappers.MoviesDataMapper;
import pt.isel.ls.tables.Movies;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MovWebAPI {
    private static final String MOVIE_DB_HOST = "https://api.themoviedb.org/3/";
    private static final String MOVIE_DB_MOVIE = "movie/%s?api_key=%s";
    private static String key;
    private static final Logger logger = LoggerFactory.getLogger(MovWebAPI.class);

    public MovWebAPI() {
        key = getAPIkey();
    }

    public void setKey(String newKey){
        if (key.equals("")){
            key = getAPIkey();
        }
        else {
            key = newKey;
        }
    }

    public Movies getMovie(int id) {
        if (key == null){
            logger.info("No API key defined during construction.");
            return null;
        }
        String url = MOVIE_DB_HOST + String.format(MOVIE_DB_MOVIE, id, key);
        try (CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpGet get = new HttpGet(url);
            try (CloseableHttpResponse resp = client.execute(get)){
                HttpEntity body = resp.getEntity();
                Scanner scanner = new Scanner(body.getContent()).useDelimiter("\\A");
                String bodyString = scanner.hasNext() ? scanner.next() : "";
                JSONObject json = new JSONObject(bodyString);
                MoviesDataMapper moviesDataMapper = new MoviesDataMapper();
                return moviesDataMapper.mapFromJSON(json);
            }
        }
        catch (IOException e){
            logger.info("JSON parsing failed.");
            return null;
        }
    }

    private String getAPIkey(){
        String key = System.getenv("API_KEY");
        if(key == null) {
            logger.info("SysEnv API_KEY not defined. Attempting to retrieve key from root file.");
            try (FileInputStream fin = new FileInputStream("API_key.txt"); Scanner scn = new Scanner(fin)) {
                key = scn.nextLine();
            } catch (IOException e) {
                logger.info("API key file not found.");
                return null;
            }
        }
        return key;
    }
}
