package unsw.loopmania;


public class EnemyFactory {
    /**
     * Creates respective Enemy
     * @param path
     * @param type
     * @return Enemy
     */
    public Enemy create(PathPosition path, String type) {
        if (type.equals("slug")) {
            return createSlug(path);
        }
        else if (type.equals("zombie")) {
            return createZombie(path);
        }
        else if (type.equals("vampire")) {
            return createVampire(path);
        }
        else if (type.equals("doggie")) {
            return createDoggie(path);
        }
        else if (type.equals("elanmuske")) {
            return createElanMuske(path);
        }
        else {
            return null;
        }
    }

    public Enemy create(String type) {
        if (type.equals("slug")) {
            return createSlug();
        }
        else if (type.equals("zombie")) {
            return createZombie();
        }
        else if (type.equals("vampire")) {
            return createVampire();
        }
        else if (type.equals("doggie")) {
            return createDoggie();
        }
        else if (type.equals("elanmuske")) {
            return createElanMuske();
        }
        else {
            return null;
        }
    }
    private Enemy createDoggie(PathPosition path) {
        return new Doggie(path);
    }
    private Enemy createElanMuske(PathPosition path) {
        return new ElanMuske(path);
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
    private Slug createSlug() {
        return new Slug();
    }
    private Zombie createZombie() {
        return new Zombie();
    }
    private Vampire createVampire() {
        return new Vampire();
    }
    private Enemy createDoggie() {
        return new Doggie();
    }
    private Enemy createElanMuske() {
        return new ElanMuske();
    }
}
