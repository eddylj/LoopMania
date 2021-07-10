package unsw.loopmania;

public class Shop {
    private boolean open;

    public Shop() {
        open = true;
    }

    public boolean getOpen() {
        return this.open;
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }
}
