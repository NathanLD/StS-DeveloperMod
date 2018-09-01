package ensisa_mod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ensisa_mod.DeveloperMod;
import ensisa_mod.patchs.AbstractCardEnum;

public class Encryption extends CustomCard{
	public static final String ID = "Encryption";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;
	private static final int ARMOR = 12;
	private static final int BONUS_ARMOR = 6;

	public Encryption() {
		super(ID, NAME, DeveloperMod.makePath(DeveloperMod.ENCRYPTION), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.DEVELOPER_COLOR,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock=ARMOR;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int count = 0;
		     for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
		       if (!mon.isDeadOrEscaped()) {
		         count++;
		       }
		     }
		     for (int i = 0; i < count; i++) {
		       AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		     }
	}

	@Override
	public AbstractCard makeCopy() {
		return new Encryption();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(BONUS_ARMOR);
		}
	}
}
