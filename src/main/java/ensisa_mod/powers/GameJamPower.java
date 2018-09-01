package ensisa_mod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ensisa_mod.DeveloperMod;
import ensisa_mod.actions.ApplyLinesOfCodeAction;

public class GameJamPower extends AbstractPower{

	public static final String POWER_ID = "GameJam";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public GameJamPower(AbstractCreature owner, int amount)
	{
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = true;
		this.img = DeveloperMod.getGameJamPowerTexture();
	}

	@Override
	public void stackPower(int stackAmount)
	{
		super.stackPower(stackAmount);
	}
	
	@Override
	public void atStartOfTurn(){
		AbstractDungeon.actionManager.addToBottom(new ApplyLinesOfCodeAction(owner, owner, this.amount));
	}

	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}


}
