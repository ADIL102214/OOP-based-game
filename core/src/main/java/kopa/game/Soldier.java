package kopa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Soldier {
    private Texture legs;

    private float aimAngle = 0;
    private float  aimSpeed = 60;

    Soldier() {
        legs = new Texture(Gdx.files.internal("legs.png"));
        legs.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    void aimUp(float delta){
        aimAngle += aimSpeed*delta;
    }

    void aimDown(float delta){
        aimAngle -= aimSpeed*delta;
    }

    float getAimAngle(){
        return aimAngle;
    }

    void draw(SpriteBatch batch, Weapon weapon, boolean firing){
        batch.draw(legs, 100, 40, 200, 200);
        if(firing){
            batch.draw(weapon.getUpperFire(), 100, 40, 64, 74, 200, 200, 1, 1, aimAngle, 0, 0, weapon.getUpperFire().getWidth(), weapon.getUpperFire().getHeight(), false, false);
        }else{
            batch.draw(weapon.getUpper(), 100, 40, 64, 74, 200, 200, 1, 1, aimAngle, 0, 0, weapon.getUpper().getWidth(), weapon.getUpper().getHeight(), false, false);
        }
    }

    public void dispose(){
        legs.dispose();
    }
}
