package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public Skin skin;
    public Stage stage;
    private final MyGdxGame context;
    public LoadingScreen(final MyGdxGame context) {
        this.context = context;
    }
    private Table uiTable;
    private ProgressBar progressBar;
    private TextButton pressAnyButtonInfo;
    private boolean isMusicLoaded;
    private SpriteBatch batch;
    private Texture imgTexture;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);

        skin=new Skin(Gdx.files.internal("sample.json"));
        stage=new Stage(new ScreenViewport());

        imgTexture = new Texture(Gdx.files.internal("splash.png"));

        uiTable = new Table();
        uiTable.setBackground(new TextureRegionDrawable(new TextureRegion(imgTexture)));
        stage.addActor(uiTable);
        uiTable.pad(20);

        //progressBar = new ProgressBar(0,1,0.1f,false,skin);
        pressAnyButtonInfo = new TextButton("pressAnyKey", skin);
        pressAnyButtonInfo.setVisible(false);
        pressAnyButtonInfo.getLabel().setWrap(true);

        uiTable.add(pressAnyButtonInfo).expand().fillX().center().row();

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        super.hide();
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
        stage.dispose();
        skin.dispose();
        batch.dispose();
    }

}
