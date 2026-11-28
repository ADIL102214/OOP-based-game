package kopa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {
    private Texture walkSheet;
    private Animation<TextureRegion> walkAnimation;
    private float stateTime = 0;

    private float x;
    private float y;
    private float width;
    private float height;
    private float speed;
    private float health;

    Enemy(float x, float y, float width, float height, float speed, float health, String walkFile, int walkFrames){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.health = health;

        walkSheet = new Texture(Gdx.files.internal(walkFile));
        walkSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion[][] walkFrame = TextureRegion.split(walkSheet, walkSheet.getWidth()/walkFrames, walkSheet.getHeight());
        walkAnimation = new Animation<>(0.2f, walkFrame[0]);
    }

    void update(float delta){
        stateTime += delta;
        this.x -= speed*delta-0.2f;
    }

    void draw(SpriteBatch batch){
        TextureRegion frame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(frame, x, y, width, height);
    }

    void dispose(){
        walkSheet.dispose();
    }

}
