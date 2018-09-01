package ensisa_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SelectOneCardBeforeBasketAction extends AbstractGameAction{

	//private static final com.megacrit.cardcrawl.localization.UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
	//public static final String[] TEXT = uiStrings.TEXT;
	public static final String[] TEXT = {"dupliquer ou exhauster :"};
	private AbstractPlayer p;
	private AbstractCard selectedCard;

	public SelectOneCardBeforeBasketAction(AbstractCreature target, AbstractCreature source, int amount)
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
			AbstractDungeon.actionManager.addToBottom(new BasketAction(p, p, 1, selectedCard));
			this.p.hand.addToTop(selectedCard);
		}

		tickDuration();
	}
}

