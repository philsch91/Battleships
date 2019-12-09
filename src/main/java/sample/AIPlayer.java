package sample;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

    private ArrayList<AIsave> AIsave = null;

    public boolean AISet() {
        this.AISetting(2);
        this.AISetting(2);
        this.AISetting(2);
        this.AISetting(2);
        this.AISetting(3);
        this.AISetting(3);
        this.AISetting(3);
        this.AISetting(4);
        this.AISetting(4);
        this.AISetting(5);

        return true;
    }

    private void AISetting(int length) {
        int x, y;
        Direction direction;
        Random random = new Random();

        do {
            x = random.nextInt((9 - 0) + 1) + 0;
            y = random.nextInt((9 - 0) + 1) + 0;
            direction = Direction.RIGHT;
            switch (random.nextInt((3 - 0) + 1) + 0) {
                case 0:
                    direction = Direction.RIGHT;
                    break;
                case 1:
                    direction = Direction.UP;
                    break;
                case 2:
                    direction = Direction.LEFT;
                    break;
                case 3:
                    direction = Direction.DOWN;
                    break;
            }
        } while (this.area.setShip(x, y, length, direction, 0, 0));
    }

    public boolean simpleAIAttack(Player enemy) {
        int x, y;
        Random random = new Random();
        do {
            x = random.nextInt((9 - 0) + 1) + 0;
            y = random.nextInt((9 - 0) + 1) + 0;
        } while (this.attackPossible(x, y));

        this.saveAttack(x, y);
        return enemy.attack(x, y);
    }

    public boolean complexAIAttack(Player enemy) {
        Random random = new Random();
        boolean result;
        int x, y;
        Direction direction;

        if (this.AIsave == null) {
            do {
                x = random.nextInt((9 - 0) + 1) + 0;
                y = random.nextInt((9 - 0) + 1) + 0;
            } while (this.attackPossible(x, y));

            this.saveAttack(x, y);
            result = enemy.attack(x, y);

            if (enemy.area.isDestroyed(x, y) != null) {
                return true;
            } else if (!result) {
                return false;
            }

            AIsave = new ArrayList<>();
            AIsave.add(new AIsave(x, y, false));

            return false;
        }

        if (AIsave.get(0).getDirection() == null) {
            direction = Direction.DOWN;
            x = (int) AIsave.get(0).getX();
            y = (int) AIsave.get(0).getY();

            do {
                switch (random.nextInt((3 - 0) + 1) + 0) {
                    case 0:
                        direction = Direction.RIGHT;
                        x++;
                        break;
                    case 1:
                        direction = Direction.UP;
                        y--;
                        break;
                    case 2:
                        direction = Direction.LEFT;
                        x--;
                        break;
                    case 3:
                        direction = Direction.DOWN;
                        y++;
                        break;
                }
            } while (this.attackPossible(x, y));

            result = enemy.attack(x, y);
            this.saveAttack(x, y);

            if (result) {
                AIsave.add(new AIsave(x, y, direction, true));
                return true;
            }

            return false;
        }

        x = (int) AIsave.get(0).getX();
        y = (int) AIsave.get(0).getY();

        if(AIsave.get(0).getDirection() == Direction.RIGHT){
            x += (int) AIsave.size();
        } else if(AIsave.get(0).getDirection() == Direction.LEFT){
            x -= (int) AIsave.size();
        } else if(AIsave.get(0).getDirection() == Direction.DOWN){
            y += (int) AIsave.size();
        } else if(AIsave.get(0).getDirection() == Direction.UP){
            y -= (int) AIsave.size();
        }

        if(!this.attackPossible(x, y)) {
            return false;
        }

        this.saveAttack(x, y);
        result = enemy.attack(x, y);

        if(result){
            AIsave.add(new AIsave(x, y, AIsave.get(0).getDirection(), false));
            return result;
        }

        if(AIsave.get(0).getDirection() == Direction.RIGHT){
            x--;
        } else if(AIsave.get(0).getDirection() == Direction.LEFT){
            x++;
        } else if(AIsave.get(0).getDirection() == Direction.DOWN){
            y--;
        } else if(AIsave.get(0).getDirection() == Direction.UP){
            y++;
        }

        if (!this.attackPossible(x, y)) {
            AIsave = null;
            return false;
        }

        AIsave a = AIsave.get(0);
        direction = Direction.LEFT;

        if(AIsave.get(0).getDirection() == Direction.RIGHT){
            direction = Direction.LEFT;
        } else if(AIsave.get(0).getDirection() == Direction.LEFT){
            direction = Direction.RIGHT;
        } else if(AIsave.get(0).getDirection() == Direction.DOWN){
            direction = Direction.UP;
        } else if(AIsave.get(0).getDirection() == Direction.UP){
            direction = Direction.DOWN;
        }

        a.setDirection(direction);
        AIsave = new ArrayList<>();
        AIsave.add(a);
        AIsave.add(new AIsave(x, y, direction, false));

        return true;
    }
}
