package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FileReader {
	
	public static JsonArray einlesen() throws FileNotFoundException {
		Gson gson = new Gson();
        // Datei "Befehlscode.json" �ber einen Stream einlesen
        FileInputStream input = new FileInputStream("src/application/Befehlscode.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        
        // Datei als JSON-Objekt einlesen
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        
        // Attribut "commands" als Array lesen
        JsonArray commands = json.getAsJsonArray("commands");
        
        return commands;
	}
	
	public static String[] befehleAlsStringArray(JsonArray commands) {
		
		String[] befehleUrsprung = new String[commands.size()];

        for(int i = 0; i < commands.size(); i++){
            JsonObject command = commands.get(i).getAsJsonObject();
            
            befehleUrsprung[i] = command.get("number").getAsString() + "," + command.get("code").getAsString();
               
            // Parameter je als JsonObject einlesen
            if (command.get("parameters").getAsJsonObject().has("x")) {
            	JsonObject parameters = command.get("parameters").getAsJsonObject(); 
                // Name des parameter ausgeben
            	
            	befehleUrsprung[i] = befehleUrsprung[i] + ",X" + parameters.get("x").getAsString();
            	befehleUrsprung[i] = befehleUrsprung[i] + ",Y" + parameters.get("y").getAsString();
            	
                if ( command.get("parameters").getAsJsonObject().has("i")) {
                	
                	befehleUrsprung[i] = befehleUrsprung[i] + ",I" + parameters.get("i").getAsString();
                	befehleUrsprung[i] = befehleUrsprung[i] + ",J" + parameters.get("j").getAsString();
                }
            }
        }
 /**        for(int i = 0; i < befehleUrsprung.length; i++) {
        	 System.out.println(befehleUrsprung[i]);
         }**/
        return befehleUrsprung;
	}
	
public static String[] befehleAlsStringArray() throws FileNotFoundException {
	
		JsonArray commands = einlesen();
		String[] befehleUrsprung = new String[commands.size()];

        for(int i = 0; i < commands.size(); i++){
            JsonObject command = commands.get(i).getAsJsonObject();
            
            befehleUrsprung[i] = command.get("number").getAsString() + "," + command.get("code").getAsString();
               
            // Parameter je als JsonObject einlesen
            if (command.get("parameters").getAsJsonObject().has("x")) {
            	JsonObject parameters = command.get("parameters").getAsJsonObject(); 
                // Name des parameter ausgeben
            	
            	befehleUrsprung[i] = befehleUrsprung[i] + ",X" + parameters.get("x").getAsString();
            	befehleUrsprung[i] = befehleUrsprung[i] + ",Y" + parameters.get("y").getAsString();
            	
                if ( command.get("parameters").getAsJsonObject().has("i")) {
                	
                	befehleUrsprung[i] = befehleUrsprung[i] + ",I" + parameters.get("i").getAsString();
                	befehleUrsprung[i] = befehleUrsprung[i] + ",J" + parameters.get("j").getAsString();
                }
            }
        }
 /**        for(int i = 0; i < befehleUrsprung.length; i++) {
        	 System.out.println(befehleUrsprung[i]);
         }**/
        return befehleUrsprung;
	}
	
	public static String[] befehleOrdnen(String[] befehleUrsprung) {
		String[] befehle = new String[befehleUrsprung.length];
		
		String temp;
		
		for (int i = 0; i < befehle.length; i++) {
			if (Integer.parseInt(String.valueOf(befehleUrsprung[i].charAt(3))) == i + 1) {
				befehle[i] = befehleUrsprung[i];
			}
			else {
				//Tauschen von aufgerufenem String und dem wo er hingeh�rt
				temp = befehleUrsprung[befehleUrsprung[i].charAt(3) - 1];
				befehleUrsprung[befehleUrsprung[i].charAt(3) - 1] = befehleUrsprung[i];
				befehleUrsprung[i] = temp;
				i = i - 1;
			}
		}
		/**for (int i = 0; i < befehle.length; i++) {
			System.out.println(befehle[i]);
		}**/
		return befehle;
	}
	
	public static String[] befehleOrdnen() throws FileNotFoundException {
		String[] befehleUrsprung = befehleAlsStringArray();
		String[] befehle = new String[befehleUrsprung.length];
		
		String temp;
		
		for (int i = 0; i < befehle.length; i++) {
			if (Integer.parseInt(String.valueOf(befehleUrsprung[i].charAt(3))) == i + 1) {
				befehle[i] = befehleUrsprung[i];
			}
			else {
				//Tauschen von aufgerufenem String und dem wo er hingeh�rt
				temp = befehleUrsprung[befehleUrsprung[i].charAt(3) - 1];
				befehleUrsprung[befehleUrsprung[i].charAt(3) - 1] = befehleUrsprung[i];
				befehleUrsprung[i] = temp;
				i = i - 1;
			}
		}
		/**for (int i = 0; i < befehle.length; i++) {
			System.out.println(befehle[i]);
		}**/
		return befehle;
	}
		
	public static String[] _getBefehl(int stelle, String[] befehle) {
		String befehl = befehle[stelle];
	/**	for (int i = 0; i < befehl.split("\\,").length; i++) {
			System.out.println(split[i]);
		}**/
		return befehl.split("\\,");
	}
	
	public static String[] _getBefehl(int stelle) throws FileNotFoundException {
		String[] befehle = befehleOrdnen();
		String befehl = befehle[stelle];
	/**	for (int i = 0; i < befehl.split("\\,").length; i++) {
			System.out.println(split[i]);
		}**/
		return befehl.split("\\,");
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
	} 
}