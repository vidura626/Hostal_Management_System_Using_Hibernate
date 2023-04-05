package lk.ijse.hostal.util;

public class TransferObjects {
    static Object object = null;
    public static void sendObject(Object object){
        TransferObjects.object = object;
    }

    public static Object recieveObject(){
        return TransferObjects.object;
    }

    public static void clear(){
        TransferObjects.object= null;
    }
}
