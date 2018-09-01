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
import ensisa_mod.patchs.AbstractCardEnum;

public class BasicHack extends CustomCard{
	public static final String ID = "BasicHack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int BONUS_DMG = 3;

	public BasicHack()
	{
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.BASIC_HACK), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
	}
	
	@Override
	public boolean isStrike() {
		return true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		if (com.megacrit.cardcrawl.core.Settings.isDebug) {
			if (com.megacrit.cardcrawl.core.Settings.isInfo) {
				this.multiDamage = new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
				for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
					this.multiDamage[i] = 150;
				}
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
			}
			else {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new DamageInfo(p, 150, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
	}

	@Override
	public AbstractCard makeCopy()
	{
		return new BasicHack();
	}

	@Override
	public void upgrade()
	{
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(BONUS_DMG);
		}
	}
}