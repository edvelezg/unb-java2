package pattern.factory;

public class ToyFactory {
    
    public static IElectronicToy CreateToy(ToyType type) throws Exception {
        IElectronicToy toy = null;
        switch (type) {
        case TeddyBear:
            toy = new TeddyBear();
            break;
        case Lion:
            toy = new Lion();
            break;
        default:
            throw new Exception();
        }
        return toy;
    }
}
