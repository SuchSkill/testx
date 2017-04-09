package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture bg;
    ShapeRenderer shapeRenderer;
    public BitmapFont font;
    private double g = 9.80665;
    private double dt = 0.2;
    private double ddt = 0.2;
    private double alfa = -45.0;
    private double x0 = 396;
    private double xx0 = 796;
    private double Ball;
    private double Cylinder;
    private double sigma;
    private float r = 64;


    double theta = 360;  // angle that will be increased each loop
    double step = 0.1;
    double theta1 = 360;
    double step1 = 0.08;

    @Override
    public void create() {//создание сцены
        alfa = Math.toRadians(alfa);
        Ball = (g * Math.sin(alfa)) / 1.4;
        Cylinder = (g * Math.sin(alfa)) / 1.5;
        batch = new SpriteBatch();
        bg = new Texture("bg1280_720_2.jpg");
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
    }


//x=a sin(A) dt
    //y=a cos (A) dt
    //a= (g sin(A))/1+1/2 для цилиндра
    //a= (g sin(A))/1+2/5 для шара
    //sigma=(g sin(A))/(r*1.5)для шара

    @Override
    public void render() {//метод который вызывается 60 раз в секунду

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();


        batch.draw(bg, 0, 0);
        font.setColor(146,48,75,100);
        font.draw(batch, "Ball", 350f, 190f);
        font.draw(batch, "Cylinder", 750f, 190f);
        batch.end();
        double xBall = Ball * Math.sin(alfa) * dt;
        double yBall = Ball * Math.cos(alfa) * dt;
        if (xBall + x0 < 532) {
//        if (yBall > 200) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.CYAN);
            shapeRenderer.circle((float) (xBall + x0), (float) (yBall + x0), r);
            shapeRenderer.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.circle((float) (xBall + x0), (float) (yBall + x0), r);
            shapeRenderer.end();
            dt += 0.2;
        } else {
            dt = 0.2;
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
//        shapeRenderer.triangle(300f,400f,300f,300f,400f,300f);
        shapeRenderer.triangle(300f, 400f, 300f, 200f, 500f, 200f);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.triangle(300f, 400f, 300f, 200f, 500f, 200f);
        shapeRenderer.end();


        double xx = Cylinder * Math.sin(alfa) * ddt;
        double yy = Cylinder * Math.cos(alfa) * ddt;
        if (xx + xx0 < 932) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.CYAN);
            shapeRenderer.circle((float) (xx + xx0), (float) (yy + x0), r);
            shapeRenderer.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.circle((float) (xx + xx0), (float) (yy + x0), r);
            shapeRenderer.end();
            ddt += 0.2;
        } else {
            ddt = 0.2;
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.triangle(700f, 400f, 700f, 200f, 900f, 200f);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.triangle(700f, 400f, 700f, 200f, 900f, 200f);
        shapeRenderer.end();


        // amount to add to theta each time (degrees)

        float z = (float) (xBall + x0 + r * Math.cos(theta));
        float z1 = (float) (yBall + x0 + r * Math.sin(theta));//(float)(x+x0),(float)(y+x0),r
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(z, z1, 4);
        shapeRenderer.end();
        theta -= step;
        if (theta == 0) {
            theta = 360;
        }


        float zz = (float) (xx + xx0 + r * Math.cos(theta1));
        float zz1 = (float) (yy + x0 + r * Math.sin(theta1));//(float)(x+x0),(float)(y+x0),r
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(zz, zz1, 4);
        shapeRenderer.end();
        theta1 -= step1;
        if (theta1 == 0) {
            theta1 = 360;
        }


    }


    @Override
    public void dispose() {
        batch.dispose();
        bg.dispose();
    }
}
