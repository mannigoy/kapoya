package com.mygdx.game.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LoadingScreen extends ScreenAdapter {
   // final Application app;
    public Skin skin;
    public Stage stage;
    private final MyGdxGame context;

    private Table uiTable;
    private TextButton pressAnyButtonInfo;
    private boolean isMusicLoaded;
    private SpriteBatch batch;
    private Texture imgTexture;
    private float progress;
    private final ShapeRenderer shapeRenderer;
    public LoadingScreen(final MyGdxGame context) {
        this.context = context;
        this.shapeRenderer = new ShapeRenderer();

    }

    private void queueAssets() {

        context.assetManager.load("sample.atlas", TextureAtlas.class);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);

        skin=new Skin(Gdx.files.internal("sample.json"));
        stage=new Stage(new ScreenViewport());

        imgTexture = new Texture("splash.png");

        uiTable = new Table();
        uiTable.setFillParent(true);
        stage.addActor(uiTable);


        pressAnyButtonInfo = new TextButton("pressAnyKey", skin,"default");
        pressAnyButtonInfo.setVisible(true);




        uiTable.setBackground(new TextureRegionDrawable(new TextureRegion(imgTexture)));

       // shapeRenderer.setProjectionMatrix(context.camera.combined);
        this.progress=0f;
        queueAssets();
    }

    @Override
    public void render(float delta) {
         Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateProgress(delta);

        batch.begin();
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (context.assetManager.update() && progress >= context.assetManager.getProgress() - .001f) {
            // Assets are loaded, show the "Press Any Button" message
            pressAnyButtonInfo.setVisible(true);
            uiTable.setBackground(new TextureRegionDrawable(new TextureRegion(imgTexture)));
        } else {
            // Assets are still loading, hide the "Press Any Button" message and progress bar
            pressAnyButtonInfo.setVisible(false);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(32, context.camera.viewportHeight / 2 - 8, context.camera.viewportWidth - 64, 16);

            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(32, context.camera.viewportHeight / 2 - 8, progress * (context.camera.viewportWidth - 64), 16);
            shapeRenderer.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }



    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
    private void updateProgress(float delta) {
        progress = MathUtils.lerp(progress, context.assetManager.getProgress(), .1f);
        if (context.assetManager.update() && progress >= context.assetManager.getProgress() - .001f) {
            if (!uiTable.getChildren().contains(pressAnyButtonInfo, true)) {
                uiTable.add(pressAnyButtonInfo).expand().fillX().center().row();
            }
        }
    }

}
