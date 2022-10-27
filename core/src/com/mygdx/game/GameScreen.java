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
	private Arquero gk;
	private ItemManager manager;
	private Texture fondo;


	public GameScreen(final GameLluviaMenu game) 
	{
	  this.game = game;
	  this.batch = game.getBatch();
	  this.font = game.getFont();
	  // load the images for the droplet and the bucket, 64x64 pixels each 	     
	  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	  gk = new Arquero(new Texture(Gdx.files.internal("arquero.png")),hurtSound);
		 
	  // load the drop sound effect and the rain background "music" 
	 Texture gota = new Texture(Gdx.files.internal("drop.png"));
	 Texture bengala = new Texture(Gdx.files.internal("bengala.png"));
	 Texture basquetBall = new Texture(Gdx.files.internal("basquetBall.png"));
	 Texture tenis = new Texture(Gdx.files.internal("tenis.png"));
	 fondo = new Texture(Gdx.files.internal("stadium monumental 2.png"));

	 
	 Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	
	 Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("colo colo.wav"));
	 manager = new ItemManager(gota, bengala, basquetBall, tenis, dropSound, rainMusic);
	  
	  batch = new SpriteBatch();
	  camera = new OrthographicCamera();
	  camera.setToOrtho(false, 800, 480);
	  // creacion del tarro
	  gk.crear();
	  
	  // creacion de la lluvia
	  manager.crear();
	}

	@Override
	public void render(float delta) 
	{
		updateCamera();
		drawHeader();
		
		// Mientras el arquero no este herido el jugador puede moverse.
		if (!gk.estaHerido()) 
		{
	        gk.actualizarMovimiento();    
	        
			// Si el arquero no posee mas vidas. 
	       if (!manager.updateMovement(gk)) 
	       {
	    	  gameOver();
	       }
		}
		
		gk.dibujar(batch);
		manager.updateDraw(batch);
		batch.end();
	}
	
	// Dibuja el encabezado de puntos, vidas y highScore.
	public void drawHeader()
	{
		batch.draw(fondo, 0, 0);
		font.draw(batch, "Puntos totales: " + gk.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + gk.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
	}
	
	// Actualiza la camara
	public void updateCamera()
	{
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		// Actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}
	
	// Manda al jugador a pantalla de GameOver.
	public void gameOver()
	{
		//Si su puntuación es la mayor se actualiza el HigherScore.
  	    if (game.getHigherScore() < gk.getPuntos())
  	    {
  	    	game.setHigherScore(gk.getPuntos());  
  	    }
  	  
  	    // Nos vamos a la pantalla de fin de juego y se cierra la actual.
  	    game.setScreen(new GameOverScreen(game));
  	    dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	// Continuar con la musica del estadio.
	public void show() 
	{
	  manager.continuar();
	}

	@Override
	public void hide() 
	{
	}

	@Override
	// Pausa el juego.
	public void pause() 
	{
		manager.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() 
	{
      gk.destruir();
      manager.destruir();
	}

}
