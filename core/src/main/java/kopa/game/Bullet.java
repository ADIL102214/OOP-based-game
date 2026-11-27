package kopa.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    private float x;
    private float y;
    private float velocityX;
    private float velocityY;
    private float angle;

    Bullet(float x, float y, float angle, float speed){
        this.x = x;
        this.y = y;
        this.angle = angle;
        velocityX = speed * (float) Math.cos(Math.toRadians(angle));
        velocityY = speed * (float) Math.sin(Math.toRadians(angle));
    }
    void update(float delta){
        x += velocityX * delta;
        y += velocityY * delta;
    }
    void draw(SpriteBatch batch, Texture texture){
        batch.draw(texture, x + 43, y + 42f, -43, -41, 80, 6, 1, 1, angle,
            0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }
    boolean isOffScreen(){
        if(x > 1280 || x < 0 || y > 720 || y < 0) {
            return true;
        }
        return false;
    }
}
