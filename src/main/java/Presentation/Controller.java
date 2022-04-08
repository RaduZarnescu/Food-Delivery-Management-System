package Presentation;

import Business.DeliveryService;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * This class is used to ensure all the controllers use the same instance of DeliveryService. All the other controllers extend this class.
 */

public class Controller {

    protected static DeliveryService deliveryService;

    public static DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public static void setDeliveryService(DeliveryService deliveryService) {
        Controller.deliveryService = deliveryService;
    }
}
