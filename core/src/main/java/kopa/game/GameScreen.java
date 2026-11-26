package kopa.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ScreenAdapter{
    private final KOPA game;
    private final Texture background;

    private final Texture legs;

    float fireTimer = 0;
    static float FIRE_DURATION = 0.1f;
    private final Texture upper;

    float aimAngle = 10;
    float aimSpeed = 60;
    private final Texture upperFire;

    private final Sound sound;
    public GameScreen(KOPA game){
        this.game = game;
        background = new Texture(Gdx.files.internal("background_lvl_1.jpg"));
        legs = new Texture(Gdx.files.internal("legs.png"));
        legs.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        upper = new Texture(Gdx.files.internal("upper.png"));
        upper.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        upperFire = new Texture(Gdx.files.internal("firing_upper_cut_final.png"));
        upperFire.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sound = Gdx.audio.newSound(Gdx.files.internal("m4.mp3"));
    }
    public void update(float delta){
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && fireTimer <= -FIRE_DURATION){
            fireTimer = FIRE_DURATION ;
            long id = sound.play(1.0f);
            sound.setPitch(id, 3f);
        }
        fireTimer -= delta*1.5f;

        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A)){
            aimAngle += aimSpeed*delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)){
            aimAngle -= aimSpeed*delta;
        }
    }
    public void render(float delta){
        update(delta);
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);

        game.batch.draw(legs, 100, 40, 200, 200);
        if(fireTimer > 0){
            game.batch.draw(upperFire, 100, 40, 64, 74, 200, 200, 1, 1, aimAngle,
                0, 0, upper.getWidth(), upper.getHeight(), false, false);
        }else{
            game.batch.draw(upper, 100, 40, 64, 74, 200, 200, 1, 1, aimAngle,
                0, 0, upper.getWidth(), upper.getHeight(), false, false);
        }

        game.batch.end();
    }
    public void dispose() {
        background.dispose();
        legs.dispose();
        upper.dispose();
        sound.dispose();
        upperFire.dispose();
    }
}
