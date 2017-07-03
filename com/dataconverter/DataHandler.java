/**
 * This java package loads and parses xml content from an file and filter out custom content
 *
 * @author Tobias Høegh <tobias@tujo.no>
 */

package com.dataconverter;

import java.util.*;//used for array and object
import com.google.gson.Gson;//here I use Gson to convert an list to a json string: https://github.com/google/gson
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
// import java.io.Serializable;

import com.dataconverter.GetURLContent;
import com.dataconverter.DataParser;

public class DataHandler {

private final String ret = "";

//this is the function where we bundle / combine all togeter. Big gathering.
public String run(String mainUrl, String subUrl, String fileName) {


        System.out.println("Fetching data ...");//show progress

        try {

                //go and get the content from the main url
                String content = new GetURLContent().fetch(mainUrl);

                //then parse the content from the main url, finding the node called "asset"
                List<List<String> > dataList = new DataParser().parseXML(content, "asset");

                //now we can loop through the parsed data list
                List<DataItem > finalList = new ArrayList<DataItem >();
                for (List<String> itm : dataList) {


                        //get the subcontent by the id, wich is the first item in the array
                        String subContent = new GetURLContent().fetch(subUrl.replace("<id>", itm.get(0)));

                        //yes, we have also here to parse the content, this time we fetch only the "title"
                        List<String> subList = new DataParser().parseJSON(subContent, "title");

                        //finaly, we create a new object, and pushing in the data we have
                        DataItem item = new DataItem(itm.get(0), itm.get(1), subList);

                        //so we can add the new object to our final list
                        finalList.add(item);

                        System.out.println("Got data with ID: " + itm.get(0));//show progress
                        // break;//to run only max one at time
                }

                //creating Gson instance to convert JSON array to Java array
                Gson converter = new Gson();

                //let's now convert Java array to JSON array
                String toJson = converter.toJson(finalList);

                //print out the json string for testing
                // System.out.println(toJson);

                //make sure we return the json string
                String ret = toJson;

                //and write this json also in a file on the disk
                if (fileName != null && !fileName.isEmpty()) {
                    File file = new File(fileName);
                    if (!file.exists()) {
                            file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);

                    //cleanup
                    bw.write(toJson);
                    bw.close();
                }


        } catch (IOException e) {
                e.printStackTrace();
        } catch (Exception e) {
                e.printStackTrace();
        }

        System.out.println("Data fetch completed. Check out " + fileName);//show progress

        return ret;
}
}




//This is the item class wich I use to hold every single data content
class DataItem {
protected HashMap<String, String> data = new HashMap<String, String>();
protected HashMap<String, List<String> > metadata = new HashMap<String, List<String> >();
public DataItem(String id, String title, List<String> list){

        //put what ever inside data or metadata
        data.put("id", id);
        data.put("title", title);
        metadata.put("related", list);
}
}
