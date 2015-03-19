package muoversiaroma;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class MuoversiARoma {
    
    final static String DEV_KEY = ""; //RICHIEDERE DEV KEY ATAC

    public static void main(String[] args) {
        
        String token = getToken();
        getInfoPalina(token,"74679");

    }
    
    public static String getToken(){
        try {      
            XmlRpcClient client = new XmlRpcClient();
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://muovi.roma.it/ws/xml/autenticazione/1"));
            client.setConfig(config);
            Object[] params = new Object[]{DEV_KEY,""};
            Object result = client.execute("autenticazione.Accedi", params); 
            return result.toString();  
        } catch (MalformedURLException ex) {
            Logger.getLogger(MuoversiARoma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XmlRpcException ex) {
            Logger.getLogger(MuoversiARoma.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return null;
    }
    public static String getInfoPalina(String token, String fermata){
        try {      
            XmlRpcClient client = new XmlRpcClient();
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://muovi.roma.it/ws/xml/paline/7"));
            client.setConfig(config);
            Object[] params = new Object[]{token,fermata,"it"};
            HashMap result = (HashMap) client.execute("paline.Previsioni", params); 
//            HashMap a = (HashMap) result.get("risposta");
//            Object b = a.get("primi_per_palina");

            
            
            return result.toString();  
        } catch (MalformedURLException ex) {
            Logger.getLogger(MuoversiARoma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XmlRpcException ex) {
            Logger.getLogger(MuoversiARoma.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return null;
    }
}
