/**
 * This java package gets xml content from an url and stores in i a file called data.xml
 *
 * @author Tobias Høegh <tobias@tujo.no>
 */

package com.dataconverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetURLContent {

//the content fetcher class
public String fetch(String url) {

        //yes, a string we are looking at
        String content = "";

        try {
                // get URL content
                URL APIURL = new URL(url);
                URLConnection conn = APIURL.openConnection();

                // open the stream and put it into BufferedReader
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                //now we build the string we return later
                String line;
                StringBuilder sb = new StringBuilder();

                //go through all lines and add thoose to the new string
                while ((line = br.readLine()) != null) {
                        sb.append(line);
                }

                //make sure we return the string
                content = sb.toString();

                //cleanup
                br.close();

        } catch (MalformedURLException e) {
                e.printStackTrace();
        } catch (Exception e) {
                e.printStackTrace();
        }

        return content;

}
}
