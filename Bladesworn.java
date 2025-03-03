// ID: 261238520

package assignment1;

public class Bladesworn extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE = 3;
    public static int BASE_ATTACK_DAMAGE;

    public Bladesworn(Tile position) {
        super(position, BASE_HEALTH,  WEAPON_TYPE, BASE_ATTACK_DAMAGE, BASE_COST);
    }

    public int takeAction() {
        Tile currentTile = getPosition();
        Monster monster = currentTile.getMonster();

        if (monster == null) {
            Tile nextTile = currentTile.towardTheCamp();
            if (nextTile != null && !nextTile.isCamp()) {
                currentTile.removeFighter(this);
                nextTile.addFighter(this);
            }
        } else {
            monster.takeDamage(getAttackDamage(), getWeaponType());
            return skillPoints(monster);

        }

        return 0;
    }

    private int skillPoints(Monster monster) {
        double damageDealt = monster.takeDamage(getAttackDamage(), getWeaponType());
        return (int) (getAttackDamage() / damageDealt + 1);
    }
}