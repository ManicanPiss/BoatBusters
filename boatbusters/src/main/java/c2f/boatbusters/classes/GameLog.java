package c2f.boatbusters.classes;


public class GameLog implements Runnable
{
	private static int numberOfLog = 1;
	private Game game;

	
	public GameLog (Game game){
		this.game = game;
	}
	
	@Override
	public void run()
	{
		Thread.currentThread().setName("GameLog-" + numberOfLog++);
		
		while (!game.gameOver())
		{
			Main.getLogger().info("Game state: ");
			synchronized (game.threadLock)
			{
				Main.getLogger().info("Current round: " + game.getRound());
				Main.getLogger().info("Score of " + game.getPlayer1().getName() + ": " + game.getPlayer1().getScore());
				Main.getLogger().info("Score of " + game.getPlayer2().getName() + ": " + game.getPlayer2().getScore());
			}
			
			try
			{
				Thread.sleep(10000L);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
