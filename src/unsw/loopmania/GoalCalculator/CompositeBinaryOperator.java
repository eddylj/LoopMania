package unsw.loopmania.GoalCalculator;

public abstract class CompositeBinaryOperator implements Composite{
    private Composite right, left;

    public CompositeBinaryOperator(Composite left, Composite right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean getValue() {
        return applyOperation(left, right);
    }

    public abstract boolean applyOperation(Composite left, Composite right);

    public int getMax(String type) {
        return Integer.max(right.getMax(type), left.getMax(type));
    }
}
