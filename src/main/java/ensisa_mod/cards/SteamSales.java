package ensisa_mod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.patchs.AbstractCardEnum;

// Créer une action pour ajouter l'argent ? (Comme les autres X)
public class SteamSales extends CustomCard{
	public static final String ID = "SteamSales";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = -1;
	private static final int GOLD = 10;
	private static final int BONUS_GOLD = 5;

	public SteamSales() {
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.STEAM_SALES), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
		this.magicNumber=this.baseMagicNumber=GOLD;
		this.exhaust=true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		//p.gold += this.energyOnUse*this.magicNumber;

		for(int i=0;i<EnergyPanel.totalCount;i++) {
			for (int j = 0; j < this.magicNumber; j++) {
				AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.GainPennyEffect(p, p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, true));
			}
		}
		p.energy.use(EnergyPanel.totalCount);
	}

	@Override
	public AbstractCard makeCopy() {
		return new SteamSales();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(BONUS_GOLD);
		}
	}
}
