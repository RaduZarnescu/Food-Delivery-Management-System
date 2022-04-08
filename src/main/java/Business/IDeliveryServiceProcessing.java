package Business;

import java.io.IOException;
import java.util.ArrayList;

public interface IDeliveryServiceProcessing {
    public void importProducts();
    public CompositeProduct createCompositeProduct(ArrayList<MenuItem>composition, String name);
    public Order createOrder(ArrayList<MenuItem>menuItems) throws IOException;
}
