package ensisa_mod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import ensisa_mod.DeveloperMod;

public class UpdatePower extends AbstractPower {

	public static final String POWER_ID = "Update";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public UpdatePower(AbstractCreature owner, int duration) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = duration;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = true;
		this.img = DeveloperMod.getUpdatePowerTexture();
	}

	@Override
	public void atStartOfTurn() {
		
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, UpdatePower.POWER_ID, 1));
		if(this.amount<=1) {		// Parce que ca reduit apres le atStartOfTurn
			this.flash();
			com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.unique.ApotheosisAction());
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, UpdatePower.POWER_ID));
		}
	}

	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}


}
