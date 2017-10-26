package BlackJack.model;

import java.util.List;
import java.util.LinkedList;

public class Player {

  private List<Card> m_hand;
  protected final int g_maxScore = 21;
  
  public Player()
  {
	  
    m_hand = new LinkedList<Card>();
  }
  
  public void DealCard(Card a_addToHand, boolean visible)
  {
	  a_addToHand.Show(visible);
	  m_hand.add(a_addToHand);
  }
  
  public Iterable<Card> GetHand()
  {
    return m_hand;
  }
  
  public void ClearHand()
  {
    m_hand.clear();
  }
  
  public void ShowHand()
  {
    for(Card c : m_hand)
    {
      c.Show(true);
    }
  }
  
  public int CalcScore()
  {
    
	  int cardScores[] = cardValues();
    
    int score = 0;

    for(Card c : GetHand()) {
        if (c.GetValue() != Card.Value.Hidden)
        {
            score += cardScores[c.GetValue().ordinal()];
        }
    }

    if (score > g_maxScore)
    {
        for(Card c : GetHand())
        {
            if (c.GetValue() == Card.Value.Ace && score > g_maxScore)
            {
                score -= 10;
            }
        }
    }

    return score;
  }
  
  public boolean CalcAce(int hitLim) {
	  
	    int cardScores[] = cardValues();
	  	
		if (CalcScore() == hitLim){
		  	
			int score = 0;
			
			// get the hand value when all Aces are valued at 1
			for (Card c : GetHand()) {
			
				if (c.GetValue() == Card.Value.Ace) {
			
					score += 1;
				} 
				else {
				
					score += cardScores[c.GetValue().ordinal()];
				}
			}
			
			// compare scores to find an Ace with a value of 11
			if (CalcScore() != score) {
			
				return true;
			}
		}
	  	
	  	return false;
    }
  
  private int[] cardValues() {
	  
	  	// the number of scores is dependent on the number of scorable values
	    // as it seems there is no way to do this check at compile time in java ?!
	    // cardScores[13] = {...};
	  
		int cv[] = {
	  			2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
	  	};
	  	assert (cv.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";
	  	
	  return cv;
  }
}