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

public class BruteForceHack extends CustomCard{
	public static final String ID = "BruteForceHack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 16;
	private static final int BONUS_DMG = 2;
	private static final int LINES_OF_CODE_AMOUNT = 10;

	public BruteForceHack()
	{
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.HACK_BRUTE_FORCE), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber=this.baseMagicNumber=LINES_OF_CODE_AMOUNT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		AbstractDungeon.actionManager.addToBottom(new ApplyLinesOfCodeAction(p, p, this.magicNumber));
		if(!upgraded) {
			for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
				if (!mon.isDeadOrEscaped()) {
					AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
					AbstractDungeon.actionManager.addToBottom(new ApplyLinesOfCodeAction(m, p, this.magicNumber));
				}
			}
		}

	}

	@Override
	public AbstractCard makeCopy()
	{
		return new BruteForceHack();
	}

	@Override
	public void upgrade()
	{
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(BONUS_DMG);
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}