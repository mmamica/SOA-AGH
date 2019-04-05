package zad4;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NBPConnector {
    private String conn_string="http://api.nbp.pl/api/exchangerates/rates/a/";
    private URL url;

    public NBPConnector(){
    }

    public String exchangeRate(String waluta) throws Exception{
        String result=connect(waluta);
        String ret="";
        Pattern p=Pattern.compile("\"mid\":(\\d*\\.?\\d*)");
        Matcher m =p.matcher(result);
        if (m.find( )) {
            ret=m.group(0);
            ret=ret.substring(6);
        }else {
            System.out.println("NO MATCH");
        }

        return ret;

    }


    public String  connect(String param) throws Exception{
        URL url = new URL(this.conn_string+param+"/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }
}