using UnityEngine;
using System.Collections;
using System.Xml.Linq;

namespace UnityStandardAssets.Characters.ThirdPerson {
public class SetUpLevel : MonoBehaviour {

		public PickUp closerBlock;
		public PickUp fartherBlock;
		public Material blue;
		public Material purple;

		private XDocument xmlDoc;
		//private TextAsset xmlDocument;
	

	// Use this for initialization
	void Start () {
		// Get strategy for the level:
		// ---------------------------
		// COMP --> "a, b"
		// PLAYER --> "c ,d"
		// (a | b) + (ca | bd) + (c | d)
		// A --> Game Manager makes blue blocks closer
		// B --> Drama Manager makes purple blocks closer
		// C --> Player chooses blue block
		// D --> Player chooses purple block
		
		char gmChoice = 'n'; // haven't made one yet
		// our language instance is from the player --> it's 
		// static so we can use it here and transfer between levels

		// If it's the beginning, then the GM can make any choice --> a random choice
		if (ThirdPersonUserControl.langInstance.Length == 0) {
			System.Random rand = new System.Random ();
			if (rand.Next (0, 2) == 0) {
				// Drama Manager should move blue closer
				gmChoice = 'a';
			} else {
				// Drama Manger should move purple closer
				gmChoice = 'b';
			}
//		// Otherwise it should follow the player's last choice
		} else {
			// Load XML File: 
			xmlDoc = XDocument.Load ("/Users/kathrynhodge/GitHub/Game-Design-via-Regular-Language/RegularLanguage-GamePlay/Assets/strategy.xml");

			// Get player's last choice --> last character put into the language
			char playerChoice = ThirdPersonUserControl.langInstance [ThirdPersonUserControl.langInstance.Length - 1];
			// Find out GM's choice by using player's choice and current langInstance
			// in the strategy file
			
			// ~~~~READ XML FILE~~~~~~~
//				for (int i = 0; i < ThirdPersonUserControl.langInstance) {
//					char elt = xmlDoc.Element("ComputerStrategy")
//				}
			
			
			// gmChoice = whatever the strategy returns
			// If strat says GM do a --> make blue blocks closer
			// If strat says GM do b --> make purple blocks closer
		}
		// Based on GM's strategy, set up the level and add it's char to the langInstance
		ThirdPersonUserControl.langInstance += gmChoice;
		print (ThirdPersonUserControl.langInstance);
		setUpLevel (gmChoice);
		
}

   // Based on our gmChoice from the strategy, we make changes...
	private void setUpLevel(char gmChoice) {
//		char closerTag = closerBlock.getCharTag ();
//		char fartherTag = fartherBlock.getCharTag ();
		Renderer closerBlockRenderer = closerBlock.GetComponent<Renderer>();
		Renderer fartherBlockRenderer = fartherBlock.GetComponent<Renderer>();
		if (gmChoice == 'a') {
			// move blue closer
				closerBlockRenderer.material = blue;
				closerBlock.charTag = 'c';
				fartherBlockRenderer.material = purple;
				fartherBlock.charTag = 'd';
			
		} else {
			// move purple closer
				closerBlockRenderer.material = blue;
				fartherBlockRenderer.material = purple;
		}
	
	}

	// Update is called once per frame
	void Update () {
	
		}
}
}
