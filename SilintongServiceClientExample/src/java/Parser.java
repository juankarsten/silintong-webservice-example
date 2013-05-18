
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.ldap.HasControls;
import javax.swing.JComboBox;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan.karsten
 */
public class Parser {
    private String parse;
    
    private static String[] keys={"idquestion","title","content","dateposted","duedate","isanswered"};
    public static String IDQUESTION= keys[0];
    public static String TITLE= keys[1];
    public static String CONTENT= keys[2];
    public static String DATEPOSTED= keys[3];
    public static String DUEDATE= keys[4];
    public static String ISANSERED= keys[5];
    
    public Parser(String parse) {
        this.parse = parse;
        
    }

    public ArrayList<HashMap<String,String>> getParse(PrintWriter out) {
        JSONArray ja=new JSONArray(parse);
        ArrayList<HashMap<String,String>> arrayList=new ArrayList<HashMap<String,String>>();
        try{
            for(int ii=0;ii<ja.length();ii++){
                JSONObject jo=ja.getJSONObject(ii);
                HashMap hashMap=new HashMap();
                for(int jj=0;jj<6;jj++){
                    hashMap.put(keys[jj], jo.getString(keys[jj]));
                }
                arrayList.add(hashMap);
            }
        }catch(Exception e){
            out.print(e.toString());
        }
        return arrayList;
    }
    
    
    
    
}
