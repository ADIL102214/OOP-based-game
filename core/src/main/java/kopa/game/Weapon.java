package kopa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Weapon {
    private Texture upper;
    private Texture upperFire;
    private Sound sound;

    float pitch;

    private int magazine;
    private int magazineSize;
    private int total;
    private float reloadTimer = 0;
    private float reloadDuration;
    private Texture reloadSheet;
    private Animation<TextureRegion> reloadAnimation;

    Weapon(String upperFile, String upperFireFile, String soundFile, float pitch, int magazineSize, int total, float reloadDuration, String reloadFile){
        upper = new Texture(Gdx.files.internal(upperFile));
        upper.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        upperFire = new Texture(Gdx.files.internal(upperFireFile));
        upperFire.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        sound = Gdx.audio.newSound(Gdx.files.internal(soundFile));
        this.pitch = pitch;

        this.magazine = magazineSize;
        this.magazineSize = magazineSize;
        this.total = total;
        this.reloadDuration = reloadDuration;
        reloadSheet = new Texture(Gdx.files.internal(reloadFile));
        reloadSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion[][] frames = TextureRegion.split(reloadSheet, reloadSheet.getWidth()/6, reloadSheet.getHeight());
        reloadAnimation = new Animation<>(reloadDuration / 5.1f, frames[0]);
    }

    Texture getUpper(){
        return upper;
    }

    Texture getUpperFire(){
        return upperFire;
    }

    void shoot(){
        long id = sound.play(1.0f);
        sound.setPitch(id, pitch);

        this.magazine--;
        this.total--;
        if(magazine == 0 && total > 0){
            reloadTimer = reloadDuration;
        }
    }

    boolean notEmpty(){
        if(magazine == 0 || reloadTimer > 0){
            return false;
        }
        return true;
    }

    boolean isReloading(){
        return reloadTimer > 0;
    }

    void update(float delta){
        if(reloadTimer > 0){
            reloadTimer -= delta;
            if(reloadTimer <= 0){
                magazine = Math.min(magazineSize, total);
            }
        }
    }

    void drawReload(SpriteBatch batch, float x, float y){
        TextureRegion frame = reloadAnimation.getKeyFrame((reloadDuration - reloadTimer)*2.3f, false);
        batch.draw(frame, x, y, 200, 200);
    }

    abstract Bullet createBullet(float x, float y, float angle);

    public void dispose(){
        sound.dispose();
        upper.dispose();
        upperFire.dispose();
        reloadSheet.dispose();
    }
}
