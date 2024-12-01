package mypackage;
abstract  class DetectionHandler
{
    DetectionHandler next;

    public void setNext(DetectionHandler next) {
        this.next = next;
    }
    public abstract  void Validate(double amount, String location, String merchant, String algo,double withdrawal);

}
class BasicCheck extends DetectionHandler
{
    @Override
    public void Validate(double amount, String location, String merchant, String algo, double withdrawal) {
        if(amount<=200||withdrawal<=200)
        {
            System.out.println("Transcation complete by BasicCheck");
        }
        else
        {
            next.Validate(amount, location, merchant, algo, withdrawal);
        }
    }
}
class Geographical extends DetectionHandler
{
    @Override
    public void Validate(double amount, String location, String merchant, String algo, double withdrawal) {
        if(location.equals("Cumilla"))
        {
            System.out.println("Transcation complete by Geographical");
        }
        else
        {
            next.Validate(amount, location, merchant, algo, withdrawal);
        }
    }
}
class Merchant extends DetectionHandler
{
    @Override
    public void Validate(double amount, String location, String merchant, String algo, double withdrawal) {
        if(merchant.equals("Black-listed"))
        {
            next.Validate(amount, location, merchant, algo, withdrawal);
        }
        else
        {
            System.out.println("transcation completed by Merchant");
        }
    }
}
class Algo extends DetectionHandler
{
    @Override
    public void Validate(double amount, String location, String merchant, String algo, double withdrawal) {
        if(algo.equals("verified"))
        {
            System.out.println("Transcation complete by algo");
        }
        else
        {
            System.out.println("Transcation reject");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DetectionHandler handler = new BasicCheck();
        DetectionHandler merchant = new Merchant();
        DetectionHandler algo = new Algo();
        DetectionHandler transcation = new Geographical();
        handler.setNext(transcation);
        transcation.setNext(merchant);
        merchant.setNext(algo);
        handler.Validate(500,"Cumilla","Normal","machine",500);

    }
}