package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen 
{
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Arquero tarro;
	private ItemManager lluvia;
	private Texture fondo;

	   
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) 
	{
	  this.game = game;
	  this.batch = game.getBatch();
 	  this.font = game.getFont();
	  // load the images for the droplet and the bucket, 64x64 pixels each 	     
	  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	  tarro = new Arquero(new Texture(Gdx.files.internal("arquero.png")),hurtSound);
		 
	  // load the drop sound effect and the rain background "music" 
	 Texture gota = new Texture(Gdx.files.internal("drop.png"));
	 Texture ladrillo = new Texture(Gdx.files.internal("ladrillo.png"));
	 Texture basquetBall = new Texture(Gdx.files.internal("basquetBall.png"));
	 fondo = new Texture(Gdx.files.internal("stadium monumental 2.png"));

	 
	 Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	
	 Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("colo colo.wav"));
	 lluvia = new ItemManager(gota, ladrillo, basquetBall, dropSound, rainMusic,fondo);
	  
	  // camera
	  camera = new OrthographicCamera();
	  camera.setToOrtho(false, 800, 480);
	  batch = new SpriteBatch();
	  // creacion del tarro
	  tarro.crear();
	  
	  // creacion de la lluvia
	  lluvia.crear();
	}

	@Override
	public void render(float delta) 
	{
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		//Texture fondo = new Texture(Gdx.files.internal("solari.png"));
		batch.draw(fondo, 0, 0);
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();        
			// caida de la lluvia 
	       if (!lluvia.actualizarMovimiento(tarro)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<tarro.getPuntos())
	    		  game.setHigherScore(tarro.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  lluvia.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() 
	{
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() 
	{
      tarro.destruir();
      lluvia.destruir();

	}

}
