import java.util.Arrays;

class Solution {
    class Unit {
        public int HP;
        public Unit() {
            this.HP = 1000;
        }
        public void underAttack(int damage) { }
    }

    class Monster @@@ {
        public int attackPoint;
        public Monster(int attackPoint) {
            this.attackPoint = attackPoint;
        }
        @@@ {
            this.HP -= damage;
        }
        @@@ {
            return attackPoint;
        }
    }

    class Warrior @@@ {
        public int attackPoint;
        public Warrior(int attackPoint) {
            this.attackPoint = attackPoint;
        }
        @@@ {
            this.HP -= damage;
        }
        @@@ {
            return attackPoint;
        }
    }

    class Healer @@@ {
        public int healingPoint;
        public Healer(int healingPoint) { 
            this.healingPoint = healingPoint;
        }
        @@@ {
            this.HP -= damage;
        }
        @@@ {
            unit.HP += healingPoint;
        }
    }
    public int[] solution(int monsterAttackPoint, int warriorAttackPoint, int healingPoint) {
        Monster monster = new Monster(monsterAttackPoint);
        Warrior warrior = new Warrior(warriorAttackPoint);
        Healer healer = new Healer(healingPoint);
        
        //전사가 몬스터를 한 번 공격
        monster.underAttack(warrior.attack());
        //몬스터가 전사를 한 번 공격
        warrior.underAttack(monster.attack());
        //몬스터가 힐러를 한 번 공격
        healer.underAttack(monster.attack());
        //힐러가 전사의 체력을 한 번 회복
        healer.healing(warrior);
        //힐러가 몬스터의 체력을 한 번 회복
        healer.healing(monster);

        int[] answer = {monster.HP, warrior.HP, healer.HP};
        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Solution sol = new Solution();
        int monsterAttackPoint = 100;
        int warriorAttackPoint = 90;
        int healingPoint = 30;
        int[] ret = sol.solution(monsterAttackPoint, warriorAttackPoint, healingPoint);

        // 실행] 버튼을 누르면 출력값을 볼 수 있습니다.
        System.out.printf("solution 메소드의 반환 값은 ");
        System.out.printf(Arrays.toString(ret));
        System.out.printf(" 입니다.\n");
    }   
}