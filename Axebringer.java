// ID: 261238520

package assignment1;

public class Axebringer extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE;
    public static int BASE_ATTACK_DAMAGE;
    private boolean axeThrown;

    public Axebringer(Tile position) {
        super(position, BASE_HEALTH, WEAPON_TYPE, BASE_ATTACK_DAMAGE, BASE_COST);
        this.axeThrown = false;
    }

    public int takeAction() {
        if (this.axeThrown) {
            this.axeThrown = false;
            return 0;
        }

        Tile currentTile = getPosition();
        Monster monster = currentTile.getMonster();

        if (monster == null) {
            Tile nextTile = currentTile.towardTheCamp();
            Monster nextMonster = nextTile.getMonster();
            if (nextMonster != null && !nextTile.isCamp()) {
                nextMonster.takeDamage(getAttackDamage(), getWeaponType());
                axeThrown = true;
                return skillPoints(nextMonster);
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
