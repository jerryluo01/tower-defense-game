package assignment1;

public abstract class Warrior extends Fighter {
    private int requiredSkillPoints;
    public static double CASTLE_DMG_REDUCTION;

    public Warrior (Tile position, double hp, int weaponType, int attackDamage, int requiredSkillPoints) {
        super(position, hp, weaponType, attackDamage);
        this.requiredSkillPoints = requiredSkillPoints;
    }

    public int getTrainingCost() {
        return requiredSkillPoints;
    }

    public double takeDamage(double rawDamage, int attackWeaponType) {
        Tile currentTile = getPosition();
        Warrior warrior = currentTile.getWarrior();

        if (currentTile.isCastle()) {
            rawDamage *= (1 - CASTLE_DMG_REDUCTION);
        }
        return super.takeDamage(rawDamage, attackWeaponType);
    }
}
