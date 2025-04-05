package assignment1;

public class Lanceforged extends Warrior{
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE;
    public static int BASE_ATTACK_DAMAGE;
    private int piercingPower;
    private int actionRange;

    public Lanceforged(Tile position, int piercingPower, int actionRange) {
        super(position, BASE_HEALTH, BASE_COST, WEAPON_TYPE, BASE_ATTACK_DAMAGE);
        this.piercingPower = piercingPower;
        this.actionRange = actionRange;
    }

    public int takeAction() {
        Tile currentTile = getPosition();

        for (int i = 0; i < this.actionRange; i++) {
            currentTile = currentTile.towardTheCamp();
            if (currentTile == null || currentTile.isCamp()) {
                break;
            }

            Monster [] monsters = currentTile.getMonsters();
            if (monsters.length != 0) {
                int monstersHit = Math.min(this.piercingPower, monsters.length);
                int skillPoints = 0;

                for (int j = 0; j < monstersHit; j++) {
                    monsters[j].takeDamage(getAttackDamage(), getWeaponType());

                    double damageDealt = monsters[j].takeDamage(getAttackDamage(), getWeaponType());

                    skillPoints += (int) (getAttackDamage() / damageDealt + 1);
                }

                return skillPoints / monstersHit;
            }
        }
        return 0;
    }
}
