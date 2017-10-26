package BlackJack.model.rules;

public class PlayerWinnerStrategy implements IWinnerStrategy {

	public boolean DealerWon(int maxScore, int dealerScore, int playerScore) {
		
		if (playerScore > maxScore) {
			
			return true;
		} else if (dealerScore > maxScore) {
			
			return false;
		}
		
		return dealerScore > playerScore;
	}
}
