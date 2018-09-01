package ensisa_mod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import ensisa_mod.DeveloperMod;

public class Headphones extends CustomRelic{
	public static final String ID = "Headphones";

	public Headphones() {
		super(ID, DeveloperMod.getHeadphonesTexture(),
				RelicTier.BOSS, LandingSound.CLINK);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
	}

	@Override
	public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
		return new Headphones();
	}
}
