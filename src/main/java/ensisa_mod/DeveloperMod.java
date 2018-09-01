package ensisa_mod;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import ensisa_mod.cards.PortableBattery;
import ensisa_mod.cards.Encryption;
import ensisa_mod.cards.Antivirus;
import ensisa_mod.cards.InternetDisruption;
import ensisa_mod.cards.EraseEvidence;
import ensisa_mod.cards.DayLight;
import ensisa_mod.cards.Update;
import ensisa_mod.cards.Firewall;
import ensisa_mod.cards.BasicHack;
import ensisa_mod.cards.BruteForceHack;
import ensisa_mod.cards.TargetedHack;
import ensisa_mod.cards.AdvancedHack;
import ensisa_mod.cards.Basket;
import ensisa_mod.cards.Pr0n;
import ensisa_mod.cards.Coffee;
import ensisa_mod.cards.WeeklyMeeting;
import ensisa_mod.cards.Snack;
import ensisa_mod.cards.SteamSales;
import ensisa_mod.cards.Training;
import ensisa_mod.characters.DeveloperCharacter;
import ensisa_mod.patchs.AbstractCardEnum;
import ensisa_mod.patchs.MyPlayerClassEnum;
import ensisa_mod.relics.Headphones;
import ensisa_mod.relics.Developer;

@SpireInitializer
public class DeveloperMod implements PostInitializeSubscriber, EditCharactersSubscriber, EditCardsSubscriber, PostDungeonInitializeSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {

	public static final Logger logger = LogManager.getLogger(DeveloperMod.class.getName());

	public static final String MODNAME = "DeveloperMod"; // mod name
	public static final String AUTHOR = "Nathan LE DIGABEL"; // your name
	public static final String DESCRIPTION = "v0.0.1 - Creating the architecture"; // description (w/ version # if you want)

	private static final String DEVELOPER_MOD_ASSETS_FOLDER = "img";
	private static final Color DEVELOPER_COLOR = CardHelper.getColor(255.0f, 153.0f, 51.0f);

	// card backgrounds
	private static final String ATTACK_DEVELOPER = "512/bg_attack_developer.png";
	private static final String SKILL_DEVELOPER = "512/bg_attack_developer.png";
	private static final String POWER_DEVELOPER = "512/bg_attack_developer.png";
	private static final String ENERGY_ORB_DEVELOPER = "512/card_developer_orb.png";

	private static final String ATTACK_DEVELOPER_PORTRAIT = "1024/bg_attack_developer.png";
	private static final String SKILL_DEVELOPER_PORTRAIT = "1024/bg_attack_developer.png";
	private static final String POWER_DEVELOPER_PORTRAIT = "1024/bg_attack_developer.png";
	private static final String ENERGY_ORB_DEVELOPER_PORTRAIT = "1024/card_developer_orb.png";

	private static final String DEVELOPER_BUTTON = "charSelect/developerButton.png";
	private static final String DEVELOPER_PORTRAIT = "charSelect/developerPortraitBG.jpg";

	// Badge
	public static final String BADGE_IMG = "FRelicBadge.png";


	// Card images
	public static final String UPDATE = "cards/update.png";
	public static final String STEAM_SALES = "cards/steam.png";
	public static final String ENCRYPTION = "cards/test.png";
	public static final String INTERNET_DISRUPTION = "cards/internet.png";
	public static final String ADVANCED_HACK = "cards/test.png";
	public static final String TARGETED_HACK = "cards/test.png";
	public static final String HACK_BRUTE_FORCE = "cards/test.png";
	public static final String BASIC_HACK = "cards/test.png";
	public static final String FIREWALL = "cards/test.png";
	public static final String ERASE_EVIDENCE = "cards/test.png";
	public static final String COFFEE = "cards/test.png";
	public static final String WEEKLY_MEETING = "cards/debate.png";
	public static final String GAMEJAM = "cards/test.png";
	public static final String BASKET = "cards/basket.png";
	public static final String PR0N = "cards/pron.png";
	public static final String ANTIVIRUS = "cards/test.png";
	public static final String SNACK = "cards/snack.png";
	public static final String DAYLIGHT = "cards/dayLight.png";
	public static final String PORTABLE_BATTERY = "cards/test.png";
	public static final String TRAINING = "cards/test.png";

	// Power images
	public static final String UPDATE_POWER = "powers/update.png";
	public static final String LINES_OF_CODE_POWER = "powers/test.png";
	public static final String COFFEE_POWER = "powers/test.png";
	public static final String GAMEJAM_POWER = "powers/test.png";
	public static final String BUG_POWER = "powers/bug.png";
	public static final String ANTIVIRUS_POWER = "powers/test.png";

	// Relic images
	public static final String DEVELOPER = "relics/developer.png";
	public static final String HEADPHONES = "relics/headphones.png";


	// Load Textures
	
	public static Texture getHeadphonesTexture() {
		return new Texture(makePath(HEADPHONES));
	}
	
	public static Texture getDeveloperTexture() {
		return new Texture(makePath(DEVELOPER));
	}
	
	public static Texture getGameJamPowerTexture() {
		return new Texture(makePath(HEADPHONES));
	}
	
	public static Texture getUpdatePowerTexture() {
		return new Texture(makePath(UPDATE_POWER));
	}

	public static Texture getLinesOfCodePowerTexture() {
		return new Texture(makePath(LINES_OF_CODE_POWER));
	}

	public static Texture getCoffeePowerTexture() {
		return new Texture(makePath(COFFEE_POWER));
	}

	public static Texture getBugPowerTexture() {
		return new Texture(makePath(BUG_POWER));
	}

	public static Texture getChouchouPowerTexture() {
		return new Texture(makePath(ANTIVIRUS_POWER));
	}

	


	/**
	 * Makes a full path for a resource path
	 * @param resource the resource, must *NOT* have a leading "/"
	 * @return the full path
	 */
	public static final String makePath(String resource) {
		return DEVELOPER_MOD_ASSETS_FOLDER + "/" + resource;
	}

	public DeveloperMod() {
		BaseMod.subscribe(this);
		BaseMod.addColor(AbstractCardEnum.DEVELOPER_COLOR.toString(),
				DEVELOPER_COLOR, DEVELOPER_COLOR, DEVELOPER_COLOR, DEVELOPER_COLOR, DEVELOPER_COLOR, DEVELOPER_COLOR, DEVELOPER_COLOR,
				makePath(ATTACK_DEVELOPER), makePath(SKILL_DEVELOPER),
				makePath(POWER_DEVELOPER), makePath(ENERGY_ORB_DEVELOPER),
				makePath(ATTACK_DEVELOPER_PORTRAIT), makePath(SKILL_DEVELOPER_PORTRAIT),
				makePath(POWER_DEVELOPER_PORTRAIT), makePath(ENERGY_ORB_DEVELOPER_PORTRAIT));
	}

	public static void initialize() {
		new DeveloperMod();
	}

	@Override
	public void receiveEditStrings() {
		logger.info("begin editting strings");

		// RelicStrings
		String relicStrings = Gdx.files.internal("localization/en/EnsisaMod-RelicStrings.json").readString(
				String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

		// CardStrings
		String cardStrings = Gdx.files.internal("localization/en/EnsisaMod-CardStrings.json").readString(
				String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

		// PowerStrings
		String powerStrings = Gdx.files.internal("localization/en/EnsisaMod-PowerStrings.json").readString(
				String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

		logger.info("done editting strings");
	}

	@Override
	public void receiveEditCards() {
		// POWERS
		BaseMod.addCard(new ensisa_mod.cards.GameJam());
		BaseMod.addCard(new Coffee());
		BaseMod.addCard(new Antivirus());

		// SKILLS
		BaseMod.addCard(new Update());
		BaseMod.addCard(new SteamSales());
		BaseMod.addCard(new Encryption());
		BaseMod.addCard(new Firewall());
		BaseMod.addCard(new WeeklyMeeting());
		BaseMod.addCard(new Basket());
		BaseMod.addCard(new Pr0n());
		BaseMod.addCard(new Snack());
		BaseMod.addCard(new PortableBattery());
		BaseMod.addCard(new Training());

		// ATTACKS
		BaseMod.addCard(new BasicHack());
		BaseMod.addCard(new AdvancedHack());
		BaseMod.addCard(new TargetedHack());
		BaseMod.addCard(new BruteForceHack());
		BaseMod.addCard(new EraseEvidence());
		BaseMod.addCard(new DayLight());

		// SPECIAL		

		// CURSES
		BaseMod.addCard(new InternetDisruption());
	}

	@Override
	public void receivePostDungeonInitialize() {
		// TODO Auto-generated method stub
	}
	
	 @Override
	    public void receivePostInitialize() {
	        // Mod badge
	        Texture badgeTexture = new Texture(makePath(BADGE_IMG));
	        ModPanel settingsPanel = new ModPanel();
	        settingsPanel.addLabel("Developer mod does not have any settings !", 400.0f, 700.0f, (me) -> {});
	        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
	        
	        /*
	        Settings.isDailyRun = false;
	        Settings.isTrial = false;
	        Settings.isDemo = false;
	        */
	    }

	@Override
	public void receiveEditKeywords() {
		String[] cancer = {"lines","line"};
		BaseMod.addKeyword(cancer,"Gives a chance to return enemy attacks against their caster.");

		String[] avertissement = {"bug","bugs"};
		BaseMod.addKeyword(avertissement,"When you have 3 bugs, add a Virus to your discard pile.");
	}

	@Override
	public void receiveEditRelics() {
		BaseMod.addRelic(new Developer(), RelicType.SHARED);
		BaseMod.addRelic(new Headphones(), RelicType.SHARED);
	}

	@Override
	public void receiveEditCharacters() {
		logger.info("begin editting characters");

		logger.info("add " + MyPlayerClassEnum.DEVELOPER_PLAYER.toString());
		BaseMod.addCharacter(DeveloperCharacter.class, "Developer", "developer character class string",
				AbstractCardEnum.DEVELOPER_COLOR.toString(), "Developer",
				makePath(DEVELOPER_BUTTON), makePath(DEVELOPER_PORTRAIT),
				MyPlayerClassEnum.DEVELOPER_PLAYER.toString());

		logger.info("done editting characters");
	}


}