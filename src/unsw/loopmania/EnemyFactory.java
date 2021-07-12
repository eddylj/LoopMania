package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class EnemyFactory {
    public Enemy create(PathPosition path, String type) {
        if (type.equals("Slug")) {
            return createSlug(path);
        }
        else if (type.equals("Zombie")) {
            return createZombie(path);
        }
        else if (type.equals("Vampire")) {
            return createVampire(path);
        }
        else {
            return null;
        }
    }

    private Slug createSlug(PathPosition path) {
        return new Slug(path);
    }
    
    private Zombie createZombie(PathPosition path) {
        return new Zombie(path);
    }
    private Vampire createVampire(PathPosition path) {
        return new Vampire(path);
    }
}
