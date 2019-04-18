package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.Direction;
import com.mygdx.game.Snake;
import com.mygdx.game.screens.PlayScreen;

public class MovingCell extends Cell {
    private Direction direction;
    private Direction nextDirection;

    //texture = "snake-body.png"

    public MovingCell(TextureAtlas atlas, String pathToTexture, TiledMap map, float x1, float y1, float width, float height) {
        super(atlas, pathToTexture, map, x1, y1, width, height);
        this.direction = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
        setBounds(SNAKE_MOVEMENT,SNAKE_MOVEMENT, SNAKE_MOVEMENT, SNAKE_MOVEMENT);
    }

    /*public MovingCell() {
        this.texture = new Texture("snake-body.png");
        this.direction = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
        setBounds(SNAKE_MOVEMENT,SNAKE_MOVEMENT, SNAKE_MOVEMENT, SNAKE_MOVEMENT);

    }

    public MovingCell(String pathToTexture) {
        this.texture = new Texture(pathToTexture);
        this.direction = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
        //defineBody();
        setBounds(SNAKE_MOVEMENT, SNAKE_MOVEMENT, SNAKE_MOVEMENT, SNAKE_MOVEMENT);
        setRegion(this.texture);

    }*/

    public MovingCell(float x, float y, TextureAtlas atlas, Direction direction, Direction nextDirection, TiledMap map, float x1, float y1, float width, float height) {
        super(atlas, Snake.BODY_TEXTURE_PATH, map, x1, y1, width, height);
        this.direction = direction;
        this.nextDirection = nextDirection;
        setBounds(x,y, SNAKE_MOVEMENT, SNAKE_MOVEMENT);
    }

    public MovingCell(float x, float y, TextureAtlas atlas, String pathToTexture, TiledMap map, float x1, float y1, float width, float height) {
        super(atlas, pathToTexture, map, x1, y1, width, height);
        this.direction = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
        setBounds(x,y, SNAKE_MOVEMENT, SNAKE_MOVEMENT);
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void updatePosition() {
        switch (nextDirection) {
            case UP:
                if(!direction.equals(Direction.UP) && direction.equals(Direction.RIGHT)) {
                    rotate90(false);
                }
                else if (!direction.equals(Direction.UP) && direction.equals(Direction.LEFT)) {
                    rotate90(true);
                }
                setPosition(getX(), getY() + SNAKE_MOVEMENT);

                if(getY() >= PlayScreen.WORLD_HEIGHT) {
                    setPosition(getX(), 0);
                }

                break;
            case DOWN:
                if(!direction.equals(Direction.DOWN) && direction.equals(Direction.RIGHT)) {
                    rotate90(true);
                }
                else if (!direction.equals(Direction.DOWN) && direction.equals(Direction.LEFT)) {
                    rotate90(false);
                }
                setPosition(getX(), getY() - SNAKE_MOVEMENT);

                if(getY() < 0) {
                    setPosition(getX(), PlayScreen.WORLD_HEIGHT - SNAKE_MOVEMENT);
                }

                break;
            case LEFT:
                if(!direction.equals(Direction.LEFT) && direction.equals(Direction.DOWN)) {
                    rotate90(true);
                }
                else if (!direction.equals(Direction.LEFT) && direction.equals(Direction.UP)) {
                    rotate90(false);
                }
                setPosition(getX() - SNAKE_MOVEMENT, getY());

                if(getX() < 0) {
                    setPosition(PlayScreen.WORLD_WIDTH - SNAKE_MOVEMENT, getY());
                }

                break;
            case RIGHT:
                if(!direction.equals(Direction.RIGHT) && direction.equals(Direction.DOWN)) {
                    rotate90(false);
                }
                else if (!direction.equals(Direction.RIGHT) && direction.equals(Direction.UP)) {
                    rotate90(true);
                }
                setPosition(getX() + SNAKE_MOVEMENT, getY());

                if(getX() >= PlayScreen.WORLD_WIDTH) {
                    setPosition(0, getY());
                }

                break;
        }
        direction = nextDirection;
    }

    public boolean checkPosition(int x, int y) {
        return (!(x == getX())) && (!(y == getY()));
    }

    public void draw(Batch batch){
        super.draw(batch);
    }
}
