package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;
import BlackJack.model.ICardDrawnObserver;

public class PlayGame implements ICardDrawnObserver {

	private Game a_game;
	private IView a_view;
	
	private boolean init;
	
	public PlayGame() {
		
		init = false;
	}
	
  public boolean Play(Game g, IView v){

	  // Initialize only once
	  // Sets important values
	  if (!init) {
		  
		  a_game = g;
		  a_view = v;
		  
		  a_game.addSubscriber(this);
		  
		  init = true;
	  }
	  
	  DisplayInfo();
	  
    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());
    }
    
    int input = a_view.GetInput();
    
    if (input == a_view.Commands("Play"))
    {	
    	a_game.NewGame();
    }
    else if (input == a_view.Commands("Hit"))
    {
        a_game.Hit();
    }
    else if (input == a_view.Commands("Stand"))
    {
        a_game.Stand();
    }

    return input != a_view.Commands("Quit");
  }
  
  public void CardDrawn() {
	// Pauses
	try {
	   DisplayInfo();
	   Thread.sleep(5000);
	} catch (Exception e) {
	}
  }
  
  public void DisplayInfo() {
	  
	// Re-render UI
  	a_view.DisplayWelcomeMessage();
    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
  }
}