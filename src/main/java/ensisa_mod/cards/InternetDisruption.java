package ensisa_mod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.actions.SelectOneCardBeforeDiscardAction;

public class InternetDisruption extends CustomCard {
	public static final String ID = "InternetDisruption";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -2;

	public InternetDisruption() {
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.INTERNET_DISRUPTION), COST, DESCRIPTION,
				AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE,
				AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE);
	}

	public void use(AbstractPlayer p, AbstractMonster m)
	{
		if (p.hasRelic("Blue Candle")) {
			useBlueCandle(p);
		} else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.UseCardAction(this));
		}
	}
	
	@Override
	public void triggerWhenDrawn() {
		AbstractDungeon.actionManager.addToBottom(new SelectOneCardBeforeDiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1));
		
	}

	@Override
	public AbstractCard makeCopy() {
		return new InternetDisruption();
	}

	@Override
	public void upgrade() {
		return;
	}
}
