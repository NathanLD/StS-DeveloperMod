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
import ensisa_mod.powers.GameJamPower;

public class GameJam extends CustomCard{
	public static final String ID = "GameJam";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int LINES_OF_CODE = 2;
    private static final int BONUS_LINES_OF_CODE = 1;
    
    public GameJam() {
        super(ID, NAME, DeveloperMod.makePath(DeveloperMod.GAMEJAM), COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.DEVELOPER_COLOR,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.magicNumber=this.baseMagicNumber=LINES_OF_CODE;
    }
    
    @Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GameJamPower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new GameJam();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(BONUS_LINES_OF_CODE);
		}
	}
}
