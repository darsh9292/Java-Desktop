/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darshan.desktop.linkextractor;

/**
 * @author Darshan Patel
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class LinkExtractor implements Runnable
{
    Thread thread=null;
    String linkStr=null;
    
    public LinkExtractor(String linkStr)
    {
        this.linkStr=linkStr;
        thread=new Thread(this);
        thread.start();
        System.out.println("Thread Started for link["+linkStr+"]");
    }
    
 
    public static List<String>extractLinks(String url) throws Exception 
    {
        final ArrayList<String> result = new ArrayList<String>();
        try 
        {
            Document doc = Jsoup.connect(url).get();

            Elements links = doc.select("a[href]");

            for (Element link : links) 
            {
                result.add(link.attr("abs:href"));
            }
        } 
        catch(Exception e) 
        {
            System.err.println("Error while extracting links"+e);
        }
       return result;
    }
    
    
    
    public void downloadWebpageForUrl(String webpageUrl)
    {
        URL url;
        InputStream is = null;
        BufferedReader br =null;
        
        FileWriter fileWrite=null;
        
        String line;

        try 
        {
            if(webpageUrl!=null && webpageUrl.length()>0)
            {
                
                url = new URL(webpageUrl);
                is = url.openStream();  // throws an IOException
                br = new BufferedReader(new InputStreamReader(is));

                fileWrite=new FileWriter(new File("E:\\Download\\web\\"+URLEncoder.encode(webpageUrl,"UTF-8")+".html"));

                while ((line = br.readLine()) != null) 
                {
                    //System.out.println(line);
                    fileWrite.append(line);

                }
            }
        }		
        catch(MalformedURLException mue) 
        {
            mue.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            try 
            {
                if(is!=null)
                    is.close();
                if(br!=null)
                    br.close();
                if(fileWrite!=null)
                    fileWrite.close();
            }
            catch(IOException ioe)
            {
                System.err.println("Error while closing io"+ioe);
            }
        }
        
    }
 
 
    public final static void main(String[] args) throws Exception
    {
        String site = "http://darshan9292.blogspot.in";
        try 
        {
            List<String> links = LinkExtractor.extractLinks(site);
            for (String link : links) 
            {
                try 
                {
                    if(link!=null && link.length()>0)
                    {
                        //downloadWebpageForUrl(link);
                        new LinkExtractor(link);
                    }
                }
                catch(Exception e)
                {
                    System.err.println("Error in inside main for link["+link+"]"+e);
                }
            }
        }
        catch(Exception e)
        {
            System.err.println("Error in main"+e);
        }
    }

    public void run() 
    {
        try 
        {
            downloadWebpageForUrl(linkStr);
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    
}