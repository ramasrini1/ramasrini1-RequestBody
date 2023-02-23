

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;


/**
 * Background: A json string representing a song will be sent in this POST request with the following fields: 
 *      songName, artistName
 */
public class JavalinSingleton {

    public static Javalin getInstance(){
        Javalin app = Javalin.create();
        ObjectMapper om = new ObjectMapper();
        
        /**
         * problem1: retrieve the song object from the request body...
         *      1. return the song object as JSON in the response body.
         * 
         * Note: Please refer to the "RequestBody.MD" file for more assistance.
         */
        app.post("/echo", ctx -> {
            
            //implement logic here
            //retrieve the json string from the request body
            String jsonString = ctx.body();

            //utilize jackson to convert the json string to a user object
    
            Song song = om.readValue(jsonString, Song.class);
    
            //now we can use the 'song' response body as a Java object in whatever way we see fit.
            //eg: return the song as the response body, but also have Javalin convert it to JSON  
            //System.out.println(song.getArtistName());
            ctx.json(song); 
                
        });

        /**
         * problem2: retrieve the song object from the request body...
         *      1. update the artist in the song object to "Beatles"
         *      2. return the updated song object as JSON in the response body
         * 
         * Note: Please refer to the "RequestBody.MD" file for more assistance.
         */
        app.post("/changeartisttobeatles", ctx -> {

            //implement logic here
             //retrieve the json string from the request body
             String jsonString = ctx.body();
             Song song = om.readValue(jsonString, Song.class);
            //change the artist name
            song.setArtistName("Beatles");
            ctx.json(song); 
               
        });


        return app;
    }
    
}
