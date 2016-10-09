package pattern.singleton.client;

import pattern.singleton.ConfigReader;

public class Client {
    public Client() {
        // TODO Auto-generated constructor stub9
    }
    public static void main(String[] args) {
        ConfigReader cr = ConfigReader.getInstance("C:\\Users\\egutarra\\workspace\\Examples\\src\\pattern\\singleton\\client\\config.txt");
        String dbconnstr = cr.getValue("10.1 64");
        System.out.println(dbconnstr);
        dbconnstr = cr.getValue("10.0 64");
        System.out.println(dbconnstr);
        dbconnstr = cr.getValue("8.8 64");
        System.out.println(dbconnstr);
    }

}
