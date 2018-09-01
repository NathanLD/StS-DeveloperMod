package ensisa_mod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ensisa_mod.DeveloperMod;

public class LinesOfCodePower extends AbstractPower{

	public static final String POWER_ID = "LinesOfCode";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	private static int MAX_LINES_OF_CODE = 50;

	public LinesOfCodePower(AbstractCreature owner, int amount)
	{
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = true;
		this.img = DeveloperMod.getLinesOfCodePowerTexture();
	}

	@Override
	public void stackPower(int stackAmount)
	{
		super.stackPower(stackAmount);
		int maxCancer = MAX_LINES_OF_CODE;
		/*if(owner==AbstractDungeon.player) {
			if(AbstractDungeon.player.hasRelic("ProjetReseau")) {
				maxCancer+=ProjetReseau.IMPROVE_MAX_CANCER;
			}
		}*/
		if (this.amount > maxCancer){
			this.amount=maxCancer;
		}
	}

	// Au début je voulais mettre le debuff à un ennemi, puis chacune de ses attaques aurait une chance de rater. Mais c'est pas possible de changer les dégats du coté Attaquant...
	// Donc je mets plutot un buff sur le perso, et les attaques le ciblant ont une chance de se retourner contre le lanceur
	@Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		// Test de confusion
		int test = (int) (Math.random()*100+1);
		if(test<this.amount) {
			this.flash();
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(info.owner, new com.megacrit.cardcrawl.cards.DamageInfo(info.owner, info.output, info.type), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SMASH));
			return 0;
		}
		return damageAmount;
	}
	
	@Override
	public void atStartOfTurn(){
		if(!AbstractDungeon.player.hasRelic("CasqueAudio") || owner!=AbstractDungeon.player) {
			this.amount--;
		}
		if (this.amount<=0) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(owner, owner, LinesOfCodePower.POWER_ID));
		}
	}

	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}


}
