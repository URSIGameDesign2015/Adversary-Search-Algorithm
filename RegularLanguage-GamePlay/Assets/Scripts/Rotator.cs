using UnityEngine;
using System.Collections;

public class Rotator : MonoBehaviour {

	// Update is called once per frame
	void Update () {
		// Making our special object spin
		// Time.deltaTime for smooth rotation
		transform.Rotate (new Vector3 (15, 30, 45) * Time.deltaTime);
	}
	
}
