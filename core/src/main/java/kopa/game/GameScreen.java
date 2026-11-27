package kopa.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class GameScreen extends ScreenAdapter{
    private KOPA game;
    private Texture background;
    private Soldier soldier;
    private Weapon weapon;

    float fireTimer = 0;
    static float FIRE_DURATION = 0.1f;

    Texture bulletTexture;
    ArrayList<Bullet> bullets = new ArrayList<>();

    public GameScreen(KOPA game){
        this.game = game;
        background = new Texture(Gdx.files.internal("background_lvl_1.jpg"));
        soldier = new Soldier();
        weapon = new AssaultRifle();
        bulletTexture = new Texture(Gdx.files.internal("bullet.png"));
    }

    public void update(float delta){
        weapon.update(delta);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && fireTimer <= -FIRE_DURATION && weapon.notEmpty()){
            fireTimer = FIRE_DURATION ;
            bullets.add(weapon.createBullet(164, 114, soldier.getAimAngle()));
            weapon.shoot();
        }
        fireTimer -= delta*1.5f;
        bullets.removeIf(b -> b.isOffScreen());
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A)){
            soldier.aimUp(delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)){
            soldier.aimDown(delta);
        }
    }
    public void render(float delta){
        update(delta);
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);
        boolean firing = fireTimer > 0;
        soldier.draw(game.batch, weapon, firing);
        for(Bullet bullet : bullets){
            bullet.draw(game.batch, bulletTexture);
            bullet.update(delta);
        }
        game.batch.end();
    }
    public void dispose() {
        background.dispose();
        soldier.dispose();
        weapon.dispose();
        bulletTexture.dispose();
    }
}
