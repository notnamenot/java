package Dvd;


public class Main {
    public static void main(String[] args) {
        MicroDVD m = new MicroDVD();
        String path = "/home/ninja/Dokumenty/s3/obiektowe/lab5/DVD/";
        try {
                m.delay(path+"gravity.txt",path+"newGravity.txt",24, 500);
        } catch(Exception e){
            e.printStackTrace();
            //e.getMessage();
        }
    }
}

