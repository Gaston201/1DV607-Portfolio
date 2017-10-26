package BlackJack.model;

import java.util.ArrayList;

import BlackJack.model.rules.*;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinnerStrategy m_winnerRule;
  
  private ArrayList<ICardDrawnObserver> subscribers;

  public Dealer(RulesFactory a_rulesFactory) {
  
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    m_winnerRule = a_rulesFactory.GetWinnerRule();
    
    subscribers = new ArrayList<ICardDrawnObserver>();
    /*for(Card c : m_deck.GetCards()) {
      c.Show(true);
      System.out.println("" + c.GetValue() + " of " + c.GetColor());
    }    */
  }
  
  public void addSubscriber(ICardDrawnObserver sub) {
	  subscribers.add(sub);
  }
  
  public void Event() {
	  
	  for (ICardDrawnObserver sub : subscribers) {
		  sub.CardDrawn();
	  }
  }
  
  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);   
    }
    return false;
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {

    	a_player.DealCard(m_deck.GetCard(), true);
    	Event();
      return true;
    }
    return false;
  }
  
  public void HitDealer(boolean visible) {
	  
	  DealCard(m_deck.GetCard(), visible);
	  Event();
  }

  public boolean IsDealerWinner(Player a_player) {
    
	  return m_winnerRule.DealerWon(g_maxScore, CalcScore(), a_player.CalcScore());
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
        return true;
    }
    return false;
  }
  
  public boolean Stand() {
	  
	  if (m_deck != null) {
		  
		  ShowHand();
		  
		  while (m_hitRule.DoHit(this)) {
			  
			  m_hitRule.DoHit(this);

			  HitDealer(true);
		  }
	  }
	  
	  return true;
  }
}






