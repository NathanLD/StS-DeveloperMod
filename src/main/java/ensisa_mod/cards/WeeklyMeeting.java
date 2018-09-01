package ensisa_mod.cards;

import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoice.Callback;
import basemod.helpers.ModalChoiceBuilder;
import basemod.helpers.TooltipInfo;
import ensisa_mod.DeveloperMod;
import ensisa_mod.actions.ApplyLinesOfCodeAction;
import ensisa_mod.patchs.AbstractCardEnum;
import ensisa_mod.powers.LinesOfCodePower;

// Créer une action pour ajouter l'argent ? (Comme les autres X)
public class WeeklyMeeting extends CustomCard implements Callback{
	public static final String ID = "WeeklyMeeting";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;
	private static final int LINES_OF_CODE = 15;

	private ModalChoice modal;

	public WeeklyMeeting() {
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.WEEKLY_MEETING), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
		this.magicNumber=this.baseMagicNumber=LINES_OF_CODE;
		
		modal = getModal();
	}

	private ModalChoice getModal() {
		return new ModalChoiceBuilder()
				.setCallback(this) // Sets callback of all the below options to this
				.setColor(CardColor.RED) // Sets color of any following cards to red
				.addOption("CONTINUE",getChoice(CardColor.RED), CardTarget.NONE)
				.setColor(CardColor.GREEN) // Sets color of any following cards to green
				.addOption("GIVE UP",getChoice(CardColor.GREEN), CardTarget.NONE)
				.create();
	}
	
	private String getChoice(CardColor cc) {
		switch(cc) {
		case RED:
			System.out.println(!upgraded);
			return(!this.upgraded)?"Tous les personnages gagnent "+this.magicNumber+" Cancer.":"Vous generez "+this.magicNumber+" Cancer.";

		case GREEN:
			System.out.println(!upgraded);
			return(!this.upgraded)?"Tous les personnages perdent leur Cancer.":"Tous les ennemis perdent leur Cancer.";

		default:
			break;
		}
		return null;
	}

	// Uses the titles and descriptions of the option cards as tooltips for this card
	@Override
	public List<TooltipInfo> getCustomTooltips()
	{
		return modal.generateTooltips();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		modal.open();
	}

	@Override
	public AbstractCard makeCopy() {
		return new WeeklyMeeting();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
			modal = getModal();
		}
	}

	// This is called when one of the option cards us chosen
	@Override
	public void optionSelected(AbstractPlayer p, AbstractMonster m, int i)
	{
		switch (i) {
		case 0:
			AbstractDungeon.actionManager.addToBottom(new ApplyLinesOfCodeAction(p, p, this.magicNumber));
			if(!upgraded) {
				for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
					if (!mon.isDeadOrEscaped()) {
						AbstractDungeon.actionManager.addToBottom(new ApplyLinesOfCodeAction(mon, p, this.magicNumber));
					}
				}
			}
			break;
		case 1:
				for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
					if (!mon.isDeadOrEscaped() && !(mon.getPower(LinesOfCodePower.POWER_ID)==null)) {
						AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(mon, mon, LinesOfCodePower.POWER_ID));
					}
				}
			if(!upgraded) {
				if (!(p.getPower(LinesOfCodePower.POWER_ID)==null)) {
					AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(p, p, LinesOfCodePower.POWER_ID));
				}
			}
			break;
		default:
			return;
		}
	}
}
