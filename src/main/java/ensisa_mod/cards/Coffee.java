package ensisa_mod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.patchs.AbstractCardEnum;
import ensisa_mod.powers.CoffeePower;

public class Coffee extends CustomCard{
	public static final String ID = "Coffee";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int HACK_DMG = 3;
    private static final int BONUS_HACK_DMG = 2;
    
    public Coffee() {
        super(ID, NAME, DeveloperMod.makePath(DeveloperMod.COFFEE), COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.DEVELOPER_COLOR,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.magicNumber=this.baseMagicNumber=HACK_DMG;
    }
    
    @Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CoffeePower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Coffee();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(BONUS_HACK_DMG);
		}
	}
}
