// ID: 261238520

package assignment1;

public class Tile {
    private boolean isCastle;
    private boolean isCamp;
    private boolean onThePath;
    private Tile towardTheCastle;
    private Tile towardTheCamp;
    private Warrior warrior;
    private MonsterTroop troop;

    public Tile() {
        this.isCastle = false;
        this.isCamp = false;
        this.onThePath = false;
        this.warrior = null;
        this.troop = new MonsterTroop();
    }

    public Tile(boolean isCastle, boolean isCamp, boolean onThePath, Tile towardTheCastle, Tile towardTheCamp, Warrior warrior, MonsterTroop troop) {
        this.isCastle = isCastle;
        this.isCamp = isCamp;
        this.onThePath = onThePath;
        this.towardTheCastle = towardTheCastle;
        this.towardTheCamp = towardTheCamp;
        this.warrior = warrior;
        this.troop = troop;
    }

    public boolean isCastle() {
        return this.isCastle;
    }

    public boolean isCamp() {
        return this.isCamp;
    }

    public void buildCastle() {
        this.isCastle = true;
    }

    public void buildCamp() {
        this.isCamp = true;
    }

    public boolean isOnThePath() {
        return this.onThePath;
    }

    public Tile towardTheCastle() {
        if (!this.onThePath || this.isCastle) {
            return null;
        } else {
            return this.towardTheCastle;
        }
    }

    public Tile towardTheCamp() {
        if (!this.onThePath || this.isCamp) {
            return null;
        } else {
            return this.towardTheCamp;
        }
    }

    public void createPath(Tile towardTheCastle, Tile towardTheCamp) {
        if ((this.isCastle() && towardTheCastle != null) || (this.isCamp() && towardTheCamp != null)) {
            throw new IllegalArgumentException("Invalid path creation.");
        }
        this.towardTheCastle = towardTheCastle;
        this.towardTheCamp = towardTheCamp;
        this.onThePath = true;
    }

    public int getNumOfMonsters() {
        return this.troop.getMonsters().length;
    }

    public Warrior getWarrior() {
        return this.warrior;
    }

    public Monster getMonster() {
        return this.troop.getFirstMonster();
    }

    public Monster [] getMonsters() {
        return this.troop.getMonsters();
    }

    public boolean addFighter(Fighter fighter) {
        if (fighter instanceof Warrior ) {
            if ((this.warrior != null) || this.isCamp) {
                return false;
            }
            this.warrior = (Warrior) fighter;
        } else if (fighter instanceof Monster ) {
            if (!this.onThePath) {
                return false;
            }
            this.troop.addMonster((Monster) fighter);
        }
        fighter.setPosition(this);
        return true;
    }

    public boolean removeFighter(Fighter fighter) {
        if (fighter instanceof Warrior ) {
            if (this.warrior == fighter) {
                this.warrior = null;
                fighter.setPosition(null);
                return true;
            }
        } else if (fighter instanceof Monster ) {
            return this.troop.removeMonster((Monster) fighter);
        }
        return false;
    }
}
