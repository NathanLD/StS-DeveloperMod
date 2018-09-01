package ensisa_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RestrictedDiscardAction extends AbstractGameAction{
	//private static final com.megacrit.cardcrawl.localization.UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
	//public static final String[] TEXT = uiStrings.TEXT;
	//public static final String[] TEXT = {"Choisissez une carte à conserver absolument :"};
	private AbstractPlayer p;
	private boolean endTurn;
	public static int numDiscarded;
	private static final float DURATION = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
	private AbstractCard forbiddenCard;

	public RestrictedDiscardAction(AbstractCreature target, AbstractCreature source, int amount, AbstractCard forbiddenCard) {
		this(target, source, amount, false, forbiddenCard);
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.DISCARD;
	}





	public RestrictedDiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean endTurn, AbstractCard forbiddenCard)
	{
		this.p = ((AbstractPlayer)target);
		setValues(target, source, amount);
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.DISCARD;
		this.endTurn = endTurn;
		this.duration = DURATION;
		this.forbiddenCard=forbiddenCard;
	}

	public void update() {
		int i;
		if (this.duration == DURATION) {
			if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
				this.isDone = true;
				return;
			}

			if (this.p.hand.size() <= this.amount) {
				this.amount = this.p.hand.size();
				int tmp = this.p.hand.size();

				for (i = 0; i < tmp; i++) {
					AbstractCard c = this.p.hand.getTopCard();
					this.p.hand.moveToDiscardPile(c);
					if (!this.endTurn) {
						c.triggerOnManualDiscard();
					}
					GameActionManager.incrementDiscard(this.endTurn);
				}

				AbstractDungeon.player.hand.applyPowers();
				tickDuration();
				return;
			}


			for (i = 0; i < this.amount; i++) {
				AbstractCard c = this.p.hand.getRandomCard(false);
				if(c!=forbiddenCard) {
					this.p.hand.moveToDiscardPile(c);
					c.triggerOnManualDiscard();
					GameActionManager.incrementDiscard(this.endTurn);
				}
				else {
					i--;
				}

			}

		}



		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
		{

			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				this.p.hand.moveToDiscardPile(c);
				c.triggerOnManualDiscard();
				GameActionManager.incrementDiscard(this.endTurn);
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}

		tickDuration();
	}
}
