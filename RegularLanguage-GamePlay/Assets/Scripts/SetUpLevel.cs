using UnityEngine;
using System.Collections;
using System.Xml.Linq;

namespace UnityStandardAssets.Characters.ThirdPerson {
public class SetUpLevel : MonoBehaviour {

		public GameObject closerBlock;
		public GameObject fartherBlock;
		public Material blue;
		public Material purple;

		private XDocument xmlDoc;
	

	// Use this for initialization
	void Start () {
		// Get strategy for the level:
		// ---------------------------
		// COMP --> "a, b"
		// PLAYER --> "c ,d"
		// (a | b) + (ca | bd) + (c | d)
		
		// Load XML File: 
		// xmlDoc = XDocument.Load ("/Users/kathrynhodge/GitHub/Game-Design-via-Regular-Language/RegularLanguage-GamePlay/Assets/");
		char gmChoice = 'n'; // haven't made one yet
		// getting our language instance from the player --> it's 
		// static so we can use it here and transfer between levels
		string langInstance = ThirdPersonUserControl.langInstance;
		if (langInstance.Length == 0) {
			System.Random rand = new System.Random ();
			if (rand.Next (0, 2) == 0) {
				// Drama Manager should move blue closer
				gmChoice = 'a';
			} else {
				// Drama Manger should move purple closer
				gmChoice = 'b';
			}
		} else {
			// Get player's last choice --> last character put into the language
			char playerChoice = langInstance [langInstance.Length - 1];
			// Check strategy via player's choice
			// ~~~~READ XML FILE~~~~~~~
			// If strat says return a --> make blue blocks closer
			// If strat says return b --> make purple blocks closer
			// do this by making whatever the strategy returns == closerBlocks
		}
		// Based on GM's strategy, set up the level
		setUpLevel (gmChoice);
	}

   // Based on our gmChoice from the strategy, we make changes...
	private void setUpLevel(char gmChoice) {
		Renderer closerBlockRenderer = closerBlock.GetComponent<Renderer>();
		Renderer fartherBlockRenderer = fartherBlock.GetComponent<Renderer>();
		if (gmChoice == 'a') {
			// move blue closer
				closerBlockRenderer.material = blue;
				fartherBlockRenderer.material = purple;
			
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
