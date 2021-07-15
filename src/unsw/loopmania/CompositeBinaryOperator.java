package unsw.loopmania;

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

}
