package kopa.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ScreenAdapter{
    private final KOPA game;
    private final Texture background;

    private final Texture soldier;

    float fireTimer = 0;
    static float FIRE_DURATION = 0.1f;
    private final Texture soldierFire;


    public GameScreen(KOPA game){
        this.game = game;
        background = new Texture(Gdx.files.internal("background_lvl_1.jpg"));
        soldier = new Texture(Gdx.files.internal("sitting.png"));
        soldier.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        soldierFire = new Texture(Gdx.files.internal("firing.png"));
    }
    public void update(float delta){
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && fireTimer <= -FIRE_DURATION){
            fireTimer = FIRE_DURATION ;
        }
        fireTimer -= delta*1.5f;
    }
    public void render(float delta){
        update(delta);
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);
        if(fireTimer > 0) {
            game.batch.draw(soldierFire, 100, 40, 200, 200);
        }else{
            game.batch.draw(soldier, 100, 40, 200, 200);
        }
        game.batch.end();
    }
    public void dispose() {
        background.dispose();
        soldier.dispose();
        soldierFire.dispose();
    }
}
