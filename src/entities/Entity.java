package entities;

import java.awt.Rectangle;

public abstract class Entity {

    protected float x, y;
    protected width, height;
    protected Rectangle hitbox;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();
        
    }
    private void initHitbox(){      //HitBox Initiator.
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitbox(){
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitbox(){
        return hitBox;
    }
}
