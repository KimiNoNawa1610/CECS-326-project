// east_village class, extends village class
public class east_village extends village{

    //east_village constructor
    public east_village(int num) {
        super(num);// call super constructor
        from();// call override from() method
    }

    //override from() method of east_village to set the direction to east
    @Override
    public void from() {
        this.name=direction.east_village;// set the direction to east
    }
}
