package ensisa_mod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.patchs.AbstractCardEnum;

public class EraseEvidence extends CustomCard{
	public static final String ID = "EraseEvidence";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 3;

	public EraseEvidence()
	{
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.ERASE_EVIDENCE), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
	}

	public List<AbstractCard> countCards() {
		List<AbstractCard> pets = new ArrayList<AbstractCard>();
		if(this.upgraded) {
			for (AbstractCard c : AbstractDungeon.player.hand.group) {
				if (isHack(c)) {
					pets.add(c);
				}
			}
		}
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			if (isHack(c)) {
				pets.add(c);
			}
		}
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			if (isHack(c)) {
				pets.add(c);
			}
		}
		return pets;
	}

	public static boolean isHack(AbstractCard c) {
		return ((c instanceof BasicHack)) || ((c instanceof TargetedHack)) || ((c instanceof BruteForceHack)) || ((c instanceof AdvancedHack));
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		for(AbstractCard c : countCards()) {
			c.calculateCardDamage(m);		// Si la carte est encore dans la drawpile, ca fait 0. Si elle a deja ete joue, ca prend les degats de la derniere fois (sans tenir compte des buffs). Donc recalcul
			c.use(p, m);
		}
	}

	@Override
	public AbstractCard makeCopy()
	{
		return new EraseEvidence();
	}

	@Override
	public void upgrade()
	{
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}