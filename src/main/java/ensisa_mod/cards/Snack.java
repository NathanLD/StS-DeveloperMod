package ensisa_mod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.patchs.AbstractCardEnum;
import ensisa_mod.powers.BugPower;

// Créer une action pour ajouter l'argent ? (Comme les autres X)
public class Snack extends CustomCard{
	public static final String ID = "Snack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ARMOR = 6;
	private static final int BONUS_ARMOR = 3;
	private static final int AVERTISSEMENTS = 1;

	public Snack() {
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.SNACK), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock=ARMOR;
		this.magicNumber=this.baseMagicNumber=AVERTISSEMENTS;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BugPower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Snack();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBlock(BONUS_ARMOR);
		}
	}
}
