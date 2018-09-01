package ensisa_mod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.actions.ApplyLinesOfCodeAction;
import ensisa_mod.patchs.AbstractCardEnum;

// Créer une action pour ajouter l'argent ? (Comme les autres X)
public class PortableBattery extends CustomCard{
	public static final String ID = "PortableBattery";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int LINES_OF_CODE = 6;
	private static final int BONUS_LINES_OF_CODE = 2;
	private static final int ENERGY = 1;

	public PortableBattery() {
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.PORTABLE_BATTERY), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.magicNumber=this.baseMagicNumber=LINES_OF_CODE;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyLinesOfCodeAction(p, p, this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p, new com.megacrit.cardcrawl.powers.EnergizedPower(p, ENERGY), ENERGY));
	}

	@Override
	public AbstractCard makeCopy() {
		return new PortableBattery();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(BONUS_LINES_OF_CODE);
		}
	}
}
