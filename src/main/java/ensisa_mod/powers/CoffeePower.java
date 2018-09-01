package ensisa_mod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ensisa_mod.DeveloperMod;
import ensisa_mod.cards.BasicHack;
import ensisa_mod.cards.BruteForceHack;
import ensisa_mod.cards.TargetedHack;
import ensisa_mod.cards.AdvancedHack;

public class CoffeePower extends AbstractPower{

	public static final String POWER_ID = "Coffee";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public CoffeePower(AbstractCreature owner, int amount)
	{
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = true;
		this.img = DeveloperMod.getCoffeePowerTexture();
		updateFarts();
	}

	@Override
	public void stackPower(int stackAmount)
	{
		super.stackPower(stackAmount);
		updateFarts();
	}

	private void updateFarts()
	{
		for (AbstractCard c : com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group) {
			if(((c instanceof BasicHack)) || ((c instanceof TargetedHack)) || ((c instanceof BruteForceHack)) || ((c instanceof AdvancedHack))){
				c.baseDamage+=this.amount;
			}
		}
		for (AbstractCard c : com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.drawPile.group) {
			if(((c instanceof BasicHack)) || ((c instanceof TargetedHack)) || ((c instanceof BruteForceHack)) || ((c instanceof AdvancedHack))){
				c.baseDamage+=this.amount;
			}
		}
		for (AbstractCard c : com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.discardPile.group) {
			if(((c instanceof BasicHack)) || ((c instanceof TargetedHack)) || ((c instanceof BruteForceHack)) || ((c instanceof AdvancedHack))){
				c.baseDamage+=this.amount;
			}
		}
	}

	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}


}
