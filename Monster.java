package assignment1;

public class Monster extends Fighter {
    private int rageLevel;
    public static int BERSERK_THRESHOLD;

    public Monster(Tile position, double hp, int weaponType, int attackDamage) {
        super(position, hp, weaponType, attackDamage);
    }

    public int takeAction() {
        // Normal action logic:
        Tile currentTile = getPosition();
        Warrior warrior = currentTile.getWarrior();
        Tile nextTile = currentTile.towardTheCastle();

        if (warrior != null) {
            warrior.takeDamage(getAttackDamage(), getWeaponType());
            currentTile.removeFighter(this);
            currentTile.addFighter(this);
        } else if (nextTile != null) {
            currentTile.removeFighter(this);
            nextTile.addFighter(this);
        }

        if (this.rageLevel >= BERSERK_THRESHOLD) {
            performExtraAction();
            this.rageLevel = 0;
        }

        return 0;
    }

    private void performExtraAction() {
        Tile currentTile = getPosition();
        Warrior warrior = currentTile.getWarrior();
        Tile nextTile = currentTile.towardTheCastle();

        if (warrior != null) {
            warrior.takeDamage(getAttackDamage(), getWeaponType());
            currentTile.removeFighter(this);
            currentTile.addFighter(this);
        } else if (nextTile != null) {
            currentTile.removeFighter(this);
            nextTile.addFighter(this);
        }
    }


    public boolean equals(Object object) {
        if (!super.equals(object)) return false;
        Monster monster = (Monster) object;
        return this.getAttackDamage() == monster.getAttackDamage();
    }

    public double takeDamage(double rawDamage, int attackWeaponType) {
        double trueDamage = super.takeDamage(rawDamage, attackWeaponType);

        if (attackWeaponType > this.getWeaponType()) {
            this.rageLevel += attackWeaponType - this.getWeaponType();
        }
        return trueDamage;
    }
}
