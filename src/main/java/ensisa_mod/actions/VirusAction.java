package ensisa_mod.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ensisa_mod.cards.InternetDisruption;

public class VirusAction extends AbstractGameAction{

	public static final List<AbstractCard> curses = new ArrayList<AbstractCard>();

	public VirusAction(AbstractCreature target, AbstractCreature source, int amount)
	{
		setValues(target, source, amount);
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
		initializeListOfCurses();
	}
	
	private void initializeListOfCurses() {
		// Add all virus
		curses.add(new InternetDisruption());
	}

	@Override
	public void update() {
		AbstractCard c=randomCurse();
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(c, 1));
		this.isDone = true;
	}
	
	private AbstractCard randomCurse() {
		Random rand = new Random();
		AbstractCard randomElement = curses.get(rand.nextInt(curses.size()));
		return randomElement;
	}

}
