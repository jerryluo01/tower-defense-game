// ID: 261238520

package assignment1;

public abstract class Fighter {
    private Tile position;
    private double health;
    private int weaponType;
    private int attackDamage;

    public Fighter (Tile position, double hp, int weaponType,int attackDamage) {
        this.position = position;
        this.health = hp;
        this.weaponType = weaponType;
        this.attackDamage = attackDamage;

        boolean addPosition = this.position.addFighter(this);
        if (!addPosition) {
            throw new IllegalArgumentException("Position is already taken");
        }
    }

    public final Tile getPosition() {
        return this.position;
    }

    public final double getHealth() {
        return this.health;
    }

    public final int getWeaponType() {
        return this.weaponType;
    }

    public final int getAttackDamage() {
        return this.attackDamage;
    }

    public void setPosition(Tile new_pos) {
        this.position = new_pos;
    }

    public double takeDamage(double rawDamage, int attackWeaponType) {
        double multiplier = 1.0;
        if (this.weaponType < attackWeaponType) {
            multiplier = 1.5;
        } else if (this.weaponType > attackWeaponType) {
            multiplier = 0.5;
        }

        double trueDamage = rawDamage * multiplier;
        this.health -= trueDamage;

        if (this.health <= 0) {
            this.position.removeFighter(this);
        }
        return trueDamage;
    }

    public abstract int takeAction();

    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != this.getClass()) return false;
        Fighter other = (Fighter) object;
        boolean posDiff = this.position == other.position;
        boolean hpDiff = Math.abs(this.health - other.health) <= 0.001;
        return posDiff && hpDiff;
    }
}