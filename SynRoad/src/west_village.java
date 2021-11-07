// west_village class, extends village class
public class west_village extends village{

    //west_village constructor
    public west_village(int num) {
        super(num);// call super constructor
        from();// call override from() method
    }

    //override from() method of west_village to set the direction to west
    @Override
    public void from() {
        this.name = direction.west_village;// set the direction to west
    }
}
