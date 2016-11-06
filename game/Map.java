package game;

import java.util.ArrayList;

import terms.Term;

public class Map {

	private static Map instance;
	
	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}
	
	int xSize = 5, ySize = 5;
	
	Tile[][] m = new Tile[xSize][ySize];
	Term[] t = new Term[6];
	Achievement[] a = new Achievement[9];

	//Didactic - d - 0
	//Hyperbole - h - 1
	//Deductive - dd - 2
	//Connotation - c - 3
	
	public void initMap() {
			
		t[0] = new Term(new String[]{"Didacticism is when the~author uses his/her writing to~teach the audience a lesson~or a moral", "Didacticism is similar to~the moral of the story but", "is sometimes used in a~negative way when the author~teaches too much", "Didacticism is very common~in medieval writing"}, "Detective,~~Now that you've learned about Didactic we~need you to find the paper that uses~didactic.", new String[]{"Suspect 1: “The Town of Davenport must~pay for what it has done! Beggars have~stolen my food numerous times! I don’t~know what to do anymore!” ", "Suspect 2: “Jim from Davenport must suffer~the consequences for he was the one who~took the food from Mr. Dean. Because Jim~stole from Mr. Dean he was put in jail~for two nights! What if you grew up to~be like Jim?”", "Suspect 3: “The people in Davenport~must behave or they will be sorry!”"}, 2);
		t[1] = new Term(new String[]{"A hyperbole is an exaggeration~which creates a greater impact~and understanding for the~audience.", "Hyperboles should not be~confused with Similes", "Similes can be hyperboles,~but not all hyperboles are~similes.", "An example of this is", "“I’m as hungry as a horse”,~which is both a simile and~a hyperbole", "Unlike “Your eyes are as~blue as the sky” which~would not be a hyperbole"}, "Detective,~~Now that you've learned about Hyperboles we~need you to find the paper that uses~a hyperbole.", new String[]{"Suspect 1: “This town will pay! I’m going to~wipe it off the face of the Earth!”", "Suspect 2: “When Jim stole my food I was~as mad as the sheriff was that time when the~looter robbed the town!”", "Suspect 3: “If the beggar who stole my food~doesn’t come clean, I’m going to need to~hunt him down myself!”"}, 1);
		t[2] = new Term(new String[]{"Deductive reasoning is when the~next step of a conclusion~follows the previously stated~fact", "Deductive reasoning is the~opposite of inductive reasoning~which makes an assumption~based on something true."},  "Detective,~~Now that you've learned about Deduction we~need you to find the paper that uses~Deduction.", new String[]{"Suspect 1: “Everyone that enters Davenport~needs to pay a toll. The Johnsons came to~Davenport last week, they must have paid~the toll.”", "Suspect 2: “Jim was near the food tent when it~was robbed. Jim also has a record. Therefore,~Jim must have stole the food.”", "Suspect 3: “I saw Alex buying fruit at the~vendor. Alex must really love fruit!”"}, 1);
		t[3] = new Term(new String[]{"Connotation is the implied~meaning or feeling of~a word besides the literal~definition of that word", "Words can have positive or~negative connotations", "Connotation can envoke~feelings in a reader", "An example of connotation~would be that the young child~ran in the flowers", "In this sense 'young' and~'flowers' makes the child~sound innocent and sweet"}, "Detective,~~Now that you've learned about Connotation we~need you to find the paper that uses~negative connotation against the town.", new String[]{"Suspect 1: “Davenport is a beautiful place~for us to vacation!”", "Suspect 2: “I need to figure out who stole my~grain from my wagon!”", "Suspect 3: “Davenport is a cheap and old~place to live. It explains why the~population is so low!”"}, 3);
		t[5] = new Term(new String[]{""}, "Detective,~~You've narrowed the list down to only three suspects.~Which of the following letters contain all of the~rhetorical devices?", new String[]{"Suspect 1: “I think I’m getting sick of this town! All~the people stealing from everybody else, we need to do~something or else Davenport is going to go away forever,~all the memories, gone.”", "Suspect 2: “I’ve lived in Davenport for hundreds of~years! The summers are warm, the kids play outside, what~could be better? Although the population is decreasing,~and if we lose too many people, the Sheriff says he would~need to quit. That means we will be left without a~sheriff soon! This is what happens when you don’t keep~up with the times!”", "Suspect 3: “I want to tell you a story about when I was~a kid. I used to come to Davenport once a year during~Christmas time. My parents wanted to get away from the~snow. Those were good times. I remember being as happy~as any other child during Christmas time.”"}, 2);
		
		a[0] = new Achievement("Detective", "detect.png");
		a[1] = new Achievement("Didactic Learned", "dl.png");
		a[2] = new Achievement("Suspect Found", "ds.png");
		a[3] = new Achievement("Hyperbole Learned", "hl.png");
		a[4] = new Achievement("Suspect Found", "hs.png");
		a[5] = new Achievement("Connotation Learned", "cl.png");
		a[6] = new Achievement("Suspect Found", "cs.png");
		a[7] = new Achievement("Deduction Learned", "ddl.png");
		a[8] = new Achievement("Suspect Found", "dds.png");
		
		m[0][0] = new Tile(0, 0, new String[]{"Welcome to Mystical!", "The town of Davenport needs~your help!", "Some mysterious person is~putting notes around the~town!", "We need your help to catch~the criminal!"}, "detect");
		m[0][1] = new Tile(0, 1, null, "ds");
		m[0][2] = new Tile(0, 2, new String[]{"A black cat scurries by"}, null);
		m[0][3] = new Tile(0, 3, new String[]{""}, null);
		m[0][4] = new Tile(0, 4, null, "ddl");
		m[1][0] = new Tile(1, 0, new String[]{"You notice dark clouds~forming"}, null);
		m[1][1] = new Tile(1, 1, null, null);
		m[1][2] = new Tile(1, 2, null, "hs");
		m[1][3] = new Tile(1, 3, new String[]{"Why are you just wandering~around?"}, null);
		m[1][4] = new Tile(1, 4, null, null);
		m[2][0] = new Tile(2, 0, null, "cl");
		m[2][1] = new Tile(2, 1, new String[]{"Hurry up! We need to find~this guy!"}, null);
		m[2][2] = new Tile(2, 2, new String[]{"You hear a crack of thunder~in the distance"}, "t");
		m[2][3] = new Tile(2, 3, new String[]{""}, null);
		m[2][4] = new Tile(2, 4, null, "dl");
		m[3][0] = new Tile(3, 0, new String[]{"There is panic among the~townspeople"},  null);
		m[3][1] = new Tile(3, 1, null, null);
		m[3][2] = new Tile(3, 2, null, "dds");
		m[3][3] = new Tile(3, 3, new String[]{"The townspeople are relying~on you"}, null);
		m[3][4] = new Tile(3, 4, null, null);
		m[4][0] = new Tile(4, 0, null, "hl");
		m[4][1] = new Tile(4, 1, new String[]{""}, null);
		m[4][2] = new Tile(4, 2, new String[]{"It begins to rain"}, null);
		m[4][3] = new Tile(4, 3, null, "cs");
		m[4][4] = new Tile(4, 4, new String[]{"You hear something in the~woods"}, null);

	}
	
	public Tile getTile(int x, int y) {
		return m[x][y];
	}
	
	public Term getTerm(int x) {
		return t[x];
	}
	
	public Achievement getAchievement(int x) {
		return a[x];
	}
	
	public int getXSize() {
		return xSize - 1;
	}
	
	public int getYSize() {
		return ySize - 1;
	}
	
	public void finish() {
		m[0][0] = new Tile(0, 0, new String[]{"Thank you detective!", "With your help we found the~culprit!", "The entire time it was", "Old Man Jenkins", "Thank you for helping us!", "We hope you can visit our~town again some day!"}, null);
		Char.getInstance().trueMessage();
	}
	
}
