package assignment1;

public class MonsterTroop {
    private Monster[] monsters;
    private int numOfMonsters;

    public MonsterTroop() {
        this.monsters = new Monster[1];
        this.numOfMonsters = 0;
    }

    public int sizeOfTroop() {
        return this.numOfMonsters;
    }

    private Monster[] createNewArray(Monster[] monsters) {
        Monster[] newmonsters = new Monster[this.monsters.length * 2];
        for (int monster = 0; monster < this.monsters.length; monster++) {
            newmonsters[monster] = monsters[monster];
        }
        return newmonsters;
    }

    public Monster[] getMonsters() {
        int length = this.monsters.length;
        if (length == this.numOfMonsters) {
            return this.monsters;
        }
        Monster[] newmonsters = new Monster[this.numOfMonsters];
        for (int monster = 0; monster < this.numOfMonsters; monster++) {
            newmonsters[monster] = this.monsters[monster];
        }
        return newmonsters;
    }

    public Monster getFirstMonster() {
        if (monsters[0] != null) {
            return monsters[0];
        }
        return null;
    }

    public void addMonster(Monster monster) {
        if (this.numOfMonsters == this.monsters.length) {
            this.monsters = createNewArray(this.monsters);
        }
        this.monsters[this.numOfMonsters] = monster;
        this.numOfMonsters++;
        }

    public boolean removeMonster(Monster monster) {
        for (int i = 0; i < this.numOfMonsters; i++) {
            if (this.monsters[i] == monster) {

                if (i == this.numOfMonsters - 1) {
                    this.monsters[i] = null;
                } else {

                    for (int j = i; j < this.numOfMonsters - 1; j++) {
                        this.monsters[j] = this.monsters[j + 1];
                    }
                    this.monsters[this.numOfMonsters - 1] = null;
                }
                this.numOfMonsters--;
                return true;
            }
        }
        return false;
    }
}
