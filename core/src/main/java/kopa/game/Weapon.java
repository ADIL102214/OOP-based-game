package kopa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public abstract class Weapon {
    private Texture upper;
    private Texture upperFire;
    private Sound sound;

    float pitch;

    Weapon(String upperFile, String upperFireFile, String soundFile, float pitch){
        upper = new Texture(Gdx.files.internal(upperFile));
        upper.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        upperFire = new Texture(Gdx.files.internal(upperFireFile));
        upperFire.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        sound = Gdx.audio.newSound(Gdx.files.internal(soundFile));
        this.pitch = pitch;
    }

    Texture getUpper(){
        return upper;
    }

    Texture getUpperFire(){
        return upperFire;
    }

    void playShotSound(){
        long id = sound.play(1.0f);
        sound.setPitch(id, pitch);
    }

    abstract Bullet createBullet(float x, float y, float angle);

    public void dispose(){
        sound.dispose();
        upper.dispose();
        upperFire.dispose();
    }
}
