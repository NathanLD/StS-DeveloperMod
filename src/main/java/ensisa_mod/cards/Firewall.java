package ensisa_mod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.patchs.AbstractCardEnum;

public class Firewall extends CustomCard{
	public static final String ID = "Firewall";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 5;
	private static final int BONUS_BLOCK = 3;

	public Firewall()
	{
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.FIREWALL), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
	}
	
	@Override
	public boolean isDefend() {
		return true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.baseBlock));
	}

	@Override
	public AbstractCard makeCopy()
	{
		return new Firewall();
	}

	@Override
	public void upgrade()
	{
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(BONUS_BLOCK);
		}
	}
}