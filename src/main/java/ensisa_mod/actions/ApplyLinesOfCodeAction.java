package ensisa_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ensisa_mod.powers.LinesOfCodePower;

public class ApplyLinesOfCodeAction extends AbstractGameAction{


	public ApplyLinesOfCodeAction(AbstractCreature target, AbstractCreature source, int amount)
	{
		setValues(target, source, amount);
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.POWER;
	}

	@Override
	public void update() {
		if ((AbstractDungeon.player.hasRelic("Developer")) && (source != null) && (source.isPlayer) && (target == source)) {
			AbstractDungeon.player.getRelic("Developer").flash();
			this.amount++;
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new LinesOfCodePower(target, amount), amount));
		this.isDone = true;
	}

}
