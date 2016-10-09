package pattern.singleton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class ConfigReader {
    private static ConfigReader m_cfgReader = null;
    private Hashtable<String, String> configTable = null;
    
    // Ensure a single instance of ConfigReader
    private ConfigReader() {
    }
    
    /**
     * getInstance: creates a single instance of ConfigReader and retrieves it when necessary.
     * @param filepath
     * @return
     */
    public static ConfigReader getInstance(String filepath) {
        if (m_cfgReader == null) {
            m_cfgReader = new ConfigReader();
            loadConfig(filepath);
        }
        return m_cfgReader;
    }

    /**
     * Load name, value pairs from OpenKingdom.set file
     * @param filepath
     */
    private static void loadConfig(String filepath) {
        System.out.println("Loading OpenKingdom.set configuration file");
        try {
            FileReader ex = new FileReader(filepath);
            String line = null;
            BufferedReader br = new BufferedReader(ex);
            m_cfgReader.configTable = new Hashtable<>();
            
            while ((line = br.readLine()) != null) {
                if (line.contains("=")) {
                    String key = line.split("=")[0];
                    String val = line.split("=")[1];
                    m_cfgReader.configTable.put(key, val);
                }
            }
            br.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the value for a particular string. In Kingdom it will be 
     * the installation path based on the version.
     * @param key
     * @return
     */
    public String getValue(String key) {
        System.out.println("getting config for key " + key);
        return (String) m_cfgReader.configTable.get(key);
    }

}
