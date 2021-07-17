package unsw.loopmania;

import java.io.FileNotFoundException;

import unsw.loopmania.PathTile.Direction;

public class TestLoopManiaWorldLoader extends LoopManiaWorldLoader{

    public TestLoopManiaWorldLoader(String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    public void onLoad(Character character) {
        // no?
    }

    @Override
    public void onLoad(PathTile pathTile, Direction into, Direction out) {
        // TODO Auto-generated method stub
        
    }
    
}
