using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class PlayerController : MonoBehaviour {

	public float speed;
	public Text countText;
	public Text winText;
	private Rigidbody rb;
	private int count;

	void Start() 
	{
		rb = GetComponent<Rigidbody> ();
		count = 0;
		SetCountText ();
		winText.text = "";
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
		if (other.gameObject.CompareTag ("Pick Up"))
		{
			other.gameObject.SetActive (false);
			count++;
			SetCountText();
		}
	}

	void SetCountText() 
	{
		countText.text = " Count: " + count.ToString ();
		if (count >= 8) 
		{
			winText.text = "You Win!";
		}
	}

	// Destorys!
	// Destroy(other.gameObject);
	
}
