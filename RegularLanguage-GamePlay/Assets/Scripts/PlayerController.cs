using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class PlayerController : MonoBehaviour {

	public float speed;
	private Rigidbody rb;

	void Start() 
	{
		rb = GetComponent<Rigidbody> ();
	}

	// Called every frame for updating things
	//void Update() {
	//}

	// Ctrl + ' for documentation

	// Called every frame for updating physics things

	void FixedUpdate() {
		float moveHorizontal = Input.GetAxis("Horizontal");
		float moveVertical = Input.GetAxis("Vertical");

		Vector3 movement = new Vector3 (moveHorizontal, 0.0f, moveVertical);

		rb.AddForce (movement * speed);

	}

	// Called when our game touches another trigger collider
	void OnTriggerEnter(Collider other) {
		// Keep track that we picked up that object
		// ----------------------------------------
		// if purple object { then add p to string}
		// if blue object { then add b to string }
		if (other.gameObject.CompareTag ("PickUpBlue"))
		{
			other.gameObject.SetActive (false);
			// add B to string
		} else if (other.gameObject.CompareTag("PickUpPurple") {
			other.gameObject.SetActive (false);
			// add P to string

		}

	}
	

	// Destorys!
	// Destroy(other.gameObject);
	
}
