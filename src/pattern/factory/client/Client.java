package pattern.factory.client;

import pattern.factory.IElectronicToy;
import pattern.factory.ToyFactory;
import pattern.factory.ToyType;

public class Client {
    public static void main(String[] args) {
        
        // Factory pattern
        try {
            IElectronicToy toy = ToyFactory.CreateToy(ToyType.Lion);
            toy.play();
            toy.stop();
            IElectronicToy toy2 = ToyFactory.CreateToy(ToyType.TeddyBear);
            toy2.play();
            toy2.stop();
        } catch (Exception e) {
            // TODO: handle exception
        }
        

    }

}
