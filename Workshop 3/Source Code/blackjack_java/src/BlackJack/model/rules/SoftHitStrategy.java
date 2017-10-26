package BlackJack.model.rules;

import BlackJack.model.Player;

class SoftHitStrategy implements IHitStrategy {
    private final int g_hitLimit = 17;

    public boolean DoHit(Player a_dealer) {

    	return a_dealer.CalcScore() < g_hitLimit || a_dealer.CalcAce(g_hitLimit);  
    }
}