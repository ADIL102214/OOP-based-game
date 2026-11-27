package kopa.game;

public class AssaultRifle extends Weapon{
    public AssaultRifle(){
        super("upper.png", "firing_upper_cut_final.png","m4.mp3", 3);
    }
    Bullet createBullet(float x, float y, float angle){
        return new Bullet(x, y, angle, 2000);
    }
}
