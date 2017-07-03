/**
 * This java package loads and parses xml content from an file and filter out custom content
 *
 * @author Tobias Høegh <tobias@tujo.no>
 */

package com.dataconverter;

import java.util.*;//used for array and object
import org.json.*;//here I use this one, cause json did not exist in my java setup: https://github.com/stleary/JSON-java
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.StringReader;
import org.xml.sax.InputSource;

public class DataParser {

private final List<String> jsonList = new ArrayList<String> ();
private final List<List<String> > xmlList = new ArrayList<List<String> >();

//json parser class
public List<String> parseJSON(String content, String field) {

        try {
                //create a new json based array. The content gets parsed on this stage.
                JSONArray arr = new JSONArray(content);

                //loop through the array items
                for (int i = 0, l = arr.length(); i < l; i++) {

                        //and get every item
                        JSONObject obj = arr.getJSONObject(i);

                        //and set the title
                        String title = obj.getString(field);

                        //and add this to the return list
                        jsonList.add(title);
                }

        } catch (Exception e) {
                e.printStackTrace();
        }

        // System.out.println("Done");

        return jsonList;
}

//xml parser class
public List<List<String> > parseXML(String content, String firstNode) {

        try {

                //create the xml document
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new InputSource(new StringReader(content)));

                //make sure it gets normalized, why? read here: https://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                doc.getDocumentElement().normalize();

                //get a list of all asset nodes
                NodeList assetList = doc.getElementsByTagName(firstNode);

                //parse through the list of asset's
                for (int i = 0, l = assetList.getLength(); i < l; i++) {

                        //get the "asset" node from the list
                        Node assetNode = assetList.item(i);

                        //check if the item is actually a node
                        if (assetNode.getNodeType() == Node.ELEMENT_NODE) {

                                //get some children nodes like metadata
                                Element assetElement = (Element) assetNode;
                                Node metadataNode = assetElement.getElementsByTagName("metadata").item(0);
                                Element metadataElement = (Element) metadataNode;

                                //check if metadata did exists as an valid node
                                if (metadataElement.getNodeType() == Node.ELEMENT_NODE) {

                                        //there we go, we have our assetId and movie title
                                        String assetId = assetElement.getAttribute("id");
                                        String movileTitle = assetElement.getElementsByTagName("title").item(0).getTextContent();

                                        //create a new array to put the data inside
                                        List<String> arr = new ArrayList<String>();
                                        arr.add(assetId);
                                        arr.add(movileTitle);

                                        //add the new array to the main list
                                        xmlList.add(arr);

                                }


                        }
                }


        } catch (Exception e) {
                e.printStackTrace();
        }

        return xmlList;
}
}
