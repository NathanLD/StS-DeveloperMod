package ensisa_mod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.actions.ApplyLinesOfCodeAction;
import ensisa_mod.patchs.AbstractCardEnum;

public class TargetedHack extends CustomCard{
	public static final String ID = "TargetedHack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 10;
	private static final int BONUS_DMG = 5;
	private static final int LINES_OF_CODE_AMOUNT = 15;
	private static final int BONUS_LINES_OF_CODE = -5;

	public TargetedHack()
	{
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.TARGETED_HACK), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber=this.baseMagicNumber=LINES_OF_CODE_AMOUNT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
			AbstractDungeon.actionManager.addToBottom(new ApplyLinesOfCodeAction(m, p, this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy()
	{
		return new TargetedHack();
	}

	@Override
	public void upgrade()
	{
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(BONUS_DMG);
			upgradeMagicNumber(BONUS_LINES_OF_CODE);
		}
	}
}