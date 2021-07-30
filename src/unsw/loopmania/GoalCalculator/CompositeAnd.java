package unsw.loopmania.GoalCalculator;

public class CompositeAnd extends CompositeBinaryOperator{

    public CompositeAnd(Composite left, Composite right) {
        super(left, right);
    }

    @Override
    public boolean applyOperation(Composite left, Composite right) {
        return left.getValue() && right.getValue();
    }
}
