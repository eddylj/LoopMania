package unsw.loopmania;

public class CompositeOr extends CompositeBinaryOperator{

    public CompositeOr(Composite left, Composite right) {
        super(left, right);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean applyOperation(Composite left, Composite right) {
        return left.getValue() || right.getValue();
    }
    
}
