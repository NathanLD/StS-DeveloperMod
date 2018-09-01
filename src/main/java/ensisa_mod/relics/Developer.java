package ensisa_mod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import ensisa_mod.DeveloperMod;
import ensisa_mod.actions.VirusAction;

public class Developer extends CustomRelic{
	public static final String ID = "Developer";

	public Developer() {
		super(ID, DeveloperMod.getDeveloperTexture(),
				RelicTier.STARTER, LandingSound.SOLID);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
	public void atPreBattle()
	{
		flash();
		AbstractDungeon.actionManager.addToBottom(new VirusAction(AbstractDungeon.player, AbstractDungeon.player, 1));
	}

	@Override
	public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
		return new Developer();
	}
}
