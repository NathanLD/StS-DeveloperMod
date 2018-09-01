package ensisa_mod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.patchs.AbstractCardEnum;
import ensisa_mod.powers.LinesOfCodePower;

public class DayLight extends CustomCard{
	public static final String ID = "DayLight";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	public static final String[] TEXT = {"This enemy doesn't have any Line of Code..."};
	private static final int COST = 2;

	public DayLight()
	{
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.DAYLIGHT), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		if (!(upgraded)) {
			if(m.hasPower(LinesOfCodePower.POWER_ID)){
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(m, p, m.getPower(LinesOfCodePower.POWER_ID).amount));
			}
			else {
				AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
			}
		}
		else {
			for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
				if (!mon.isDeadOrEscaped()) {
					AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(mon, p, mon.getPower(LinesOfCodePower.POWER_ID).amount));
				}
			}
		}
	}

	@Override
	public AbstractCard makeCopy()
	{
		return new DayLight();
	}

	@Override
	public void upgrade()
	{
		if (!this.upgraded) {
			upgradeName();
			this.target = AbstractCard.CardTarget.ALL_ENEMY;
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}