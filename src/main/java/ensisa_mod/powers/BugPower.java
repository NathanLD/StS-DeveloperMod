package ensisa_mod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ensisa_mod.DeveloperMod;
import ensisa_mod.actions.VirusAction;

public class BugPower extends AbstractPower{

	public static final String POWER_ID = "Bug";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	private static int MAX_AVERTISSEMENTS = 3;

	public BugPower(AbstractCreature owner, int amount)
	{
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.DEBUFF;
		this.isTurnBased = true;
		this.img = DeveloperMod.getBugPowerTexture();
	}

	@Override
	public void stackPower(int stackAmount)
	{
		super.stackPower(stackAmount);
		int maxAvertissements = MAX_AVERTISSEMENTS;
		if(owner==AbstractDungeon.player) {
			if(AbstractDungeon.player.hasPower(AntivirusPower.POWER_ID)) {
				maxAvertissements+=AbstractDungeon.player.getPower(AntivirusPower.POWER_ID).amount;
			}
		}
		
		while(this.amount >= maxAvertissements){
			int exces = this.amount-maxAvertissements;
			this.amount=exces;
			AbstractDungeon.actionManager.addToBottom(new VirusAction(owner, owner, 1));
		}
		if(this.amount==0) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(owner, owner, BugPower.POWER_ID));
		}
	}

	@Override
	public void updateDescription() {
		int maxAvertissements = 3;
		if(owner==AbstractDungeon.player) {
			if(AbstractDungeon.player.hasPower(AntivirusPower.POWER_ID)) {
				maxAvertissements+=AbstractDungeon.player.getPower(AntivirusPower.POWER_ID).amount;
			}
		}
		this.description = DESCRIPTIONS[0] + maxAvertissements + DESCRIPTIONS[1];
	}


}
