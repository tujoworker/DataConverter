/**
 * This java package loads and parses xml content from an file and filter out custom content
 *
 * @author Tobias Høegh <tobias@tujo.no>
 */

package com.dataconverter;

import java.util.*;//used for array and object
import org.json.*;//here I use this one, cause json did not exist in my java setup: https://github.com/stleary/JSON-java

import com.dataconverter.GetURLContent;
import com.dataconverter.DataParser;

public class DataHandler {

//this is the function where we bundle / combine all togeter. Big gathering.
public String run(String mainUrl, String subUrl) {

        //default return;
        String ret = "[]";

        try {

                //go and get the content from the main url
                String content = new GetURLContent().fetch(mainUrl);

                //then parse the content from the main url, finding the node called "asset"
                List<List<String> > dataList = new DataParser().parseXML(content, "asset");

                //now we can loop through the parsed data list
                List<JSONObject > finalList = new ArrayList<JSONObject >();

                //lets loop though the dataList so we finely can crate the new API
                for (List<String> itm : dataList) {


                        //get the subcontent by the id, wich is the first item in the array
                        String subContent = new GetURLContent().fetch(subUrl.replace("<id>", itm.get(0)));

                        //yes, we have also here to parse the content, this time we fetch only the "title"
                        List<String> subList = new DataParser().parseJSON(subContent, "title");
                        
                        //now I create the main containers for the json
                        JSONObject item = new JSONObject();
                        JSONObject data = new JSONObject();
                        JSONObject metadata = new JSONObject();

                        //add some data to the first container
                        data.put("id", itm.get(0));
                        data.put("title", itm.get(1));

                        //and some to the next one
                        metadata.put("related", subList);

                        //now, let's put all together
                        item.put("data", data);
                        item.put("metadata", metadata);

                        //and add the item to the list
                        finalList.add(item);

                        // System.out.println("Data: " + item.toString());//for testing
                        // break;//to run only max one at time

                }

                //convert the list to a json array
                JSONArray json = new JSONArray(finalList);

                //and make sure we return it as a json string
                ret = json.toString();

        } catch (Exception e) {
                e.printStackTrace();
        }

        return ret;
}
}
