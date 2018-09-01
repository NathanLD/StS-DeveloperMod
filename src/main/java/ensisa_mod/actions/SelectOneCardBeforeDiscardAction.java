package ensisa_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SelectOneCardBeforeDiscardAction extends AbstractGameAction{

	//private static final com.megacrit.cardcrawl.localization.UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
	//public static final String[] TEXT = uiStrings.TEXT;
	public static final String[] TEXT = {"conserver absolument :"};
	private AbstractPlayer p;
	private AbstractCard selectedCard;
	//public static int numExhausted;

	public SelectOneCardBeforeDiscardAction(AbstractCreature target, AbstractCreature source, int amount)
	{
		this.p = ((AbstractPlayer)target);
		setValues(target, source, amount);
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.SPECIAL;
	}

	public void update() {
		if (this.duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST) {
			if (this.p.hand.size() < 1) {
				return;
			}

			else {

				//numPlaced = this.amount;
				AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
				tickDuration();
				return;
			}
		}

		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for (com.megacrit.cardcrawl.cards.AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				selectedCard=c;
			}
			AbstractDungeon.player.hand.refreshHandLayout();
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
			AbstractDungeon.actionManager.addToBottom(new RestrictedDiscardAction(p, p, 1, selectedCard));
			this.p.hand.addToTop(selectedCard);
		}

		tickDuration();
	}
}

