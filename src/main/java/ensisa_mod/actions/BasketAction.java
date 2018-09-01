package ensisa_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoice.Callback;
import basemod.helpers.ModalChoiceBuilder;

public class BasketAction extends AbstractGameAction implements Callback{
	private static final float DURATION = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;

	private AbstractCard selectedCard;
	private ModalChoice modal;


	public BasketAction(AbstractCreature target, AbstractCreature source, int amount, AbstractCard selectedCard)
	{
		setValues(target, source, amount);
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.SPECIAL;
		this.duration = DURATION;
		this.selectedCard=selectedCard;
		modal = getModal();
	}

	private ModalChoice getModal() {
		return new ModalChoiceBuilder()
				.setCallback(this) // Sets callback of all the below options to this
				.setColor(CardColor.RED) // Sets color of any following cards to red
				.addOption("BASKET!","Exhaust the chosen card.", CardTarget.NONE)
				.setColor(CardColor.GREEN) // Sets color of any following cards to green
				.addOption("REBOUND","Duplicate the chosen card.", CardTarget.NONE)
				.create();
	}

	// This is called when one of the option cards us chosen
	@Override
	public void optionSelected(AbstractPlayer p, AbstractMonster m, int i)
	{
		switch (i) {
		case 0:
			AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction(selectedCard, AbstractDungeon.player.hand));
			break;
		case 1:
			AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(selectedCard.makeStatEquivalentCopy()));
			break;
		default:
			return;
		}
	}

	public void update() {
		modal.open();
		this.isDone = true;
		tickDuration();
	}
}
