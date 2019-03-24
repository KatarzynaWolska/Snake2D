package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class SnakeBody extends Sprite {
    public World world;
    public Body body;

    private Texture texture;

    public SnakeBody (World world) {
        this.world = world;
        this.texture = new Texture("snake-body.png");
        defineBody();
        setBounds(0 ,0, 16, 16);
        setRegion(this.texture);
    }

    public void update(float deltaTime) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
    }

    public void draw(Batch batch){
        super.draw(batch);
    }

    private void defineBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 , 32);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(10);

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);
    }

}
