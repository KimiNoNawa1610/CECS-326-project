// driver class to test deadlock and muti-thread processing
public class main {
    public static void main(String[] args){

        // initialize multiple thread as villagers
        int index=0;
        Thread westVillage=new west_village(index);
        Thread eastVillage=new east_village(index+1);
        Thread westVillage1=new west_village(index+2);
        Thread eastVillage1=new east_village(index+3);
        Thread westVillage2=new west_village(index+4);
        Thread eastVillage2=new east_village(index+5);

        // start all threads
        eastVillage.start();
        westVillage.start();
        eastVillage1.start();
        westVillage1.start();
        eastVillage2.start();
        westVillage2.start();


    }
}
