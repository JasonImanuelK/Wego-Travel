public class Singleton {
    private static Singleton instance = null;

    public String token;

    private Singleton(){
        accountLoginToken = "";
    }

    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }

        return instance;
    }
}