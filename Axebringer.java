package assignment1;

public class Axebringer extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE = 2;
    public static int BASE_ATTACK_DAMAGE;
    private boolean axeThrown;

    public Axebringer(Tile position) {
        super(position, BASE_HEALTH, WEAPON_TYPE, BASE_ATTACK_DAMAGE, BASE_COST);
        this.axeThrown = false;
    }

    public int takeAction() {
        double damageDealt;

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
                damageDealt = nextMonster.takeDamage(getAttackDamage(), getWeaponType());
                axeThrown = true;
                return skillPoints(damageDealt);
            }
        } else {
            damageDealt = monster.takeDamage(getAttackDamage(), getWeaponType());
            return skillPoints(damageDealt);
        }
        return 0;
    }

    private int skillPoints(double damageDealt) {
        return (int) (getAttackDamage() / damageDealt + 1);
    }
}
