package BlackJack.model.rules;

public interface IWinnerStrategy {

	boolean DealerWon(int maxScore, int dealerScore, int playerScore);
}