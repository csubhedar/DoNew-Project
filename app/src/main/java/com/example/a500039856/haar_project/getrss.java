package com.example.a500039856.haar_project;

import android.app.Activity;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by This Pc on 22-02-2017.
 */
 public class getrss extends AsyncTask<Void,Void,Void> {

    private static final String TAG = "SERVICE";

    String address = "http://www.amazon.in/gp/rss/bestsellers/grocery/ref=zg_bs_grocery_rsslink";
    URL url;
    ArrayList<item> feeditems;
    RecyclerView recyclerView;
    Context context;
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        ItemAdapter adapter=new ItemAdapter(context,feeditems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    public getrss(Context context,RecyclerView recyclerView) {
        this.context=context;
        this.recyclerView=recyclerView;
        ProgressDialog pd=new ProgressDialog(context);
        pd.setMessage("Loading...");
        pd.show();
    }

    private void ProcessXml(Document data) {
        if(data!=null)
        {
            feeditems=new ArrayList<>();
            Log.v(TAG,"here");
         Element root=data.getDocumentElement();
            Node channel=root.getChildNodes().item(1);
            NodeList items=channel.getChildNodes();
            for(int i=0;i<items.getLength();i++)
            {
                Node currentchild=items.item(i);
                if(currentchild.getNodeName().equalsIgnoreCase("item"))
                {
                    item newi=new item();

                    NodeList itemschild=currentchild.getChildNodes();
                    for(int j=0;j<itemschild.getLength();j++)
                    {
                        Node itemchild=itemschild.item(j);
                        if(itemchild.getNodeName().equalsIgnoreCase("title"))
                            newi.setTitle(itemchild.getTextContent());
                        else if(itemchild.getNodeName().equalsIgnoreCase("link"))
                            newi.setLink(itemchild.getTextContent());
                        else if(itemchild.getNodeName().equalsIgnoreCase("description"))
                            newi.setDescription(itemchild.getTextContent());
                    }
                    feeditems.add(newi);
                }
            }
        }
    }

    public Document getData() {
        try {

            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmldoc = builder.parse(inputStream);
            return xmldoc;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();

        }
        return null;
    }


    @Override
    protected Void doInBackground(Void... params) {
        try{
            ProcessXml(getData());
        }
        catch (Exception e){}
        return null;
    }
}
