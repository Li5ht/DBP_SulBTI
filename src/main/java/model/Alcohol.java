package model;

public class Alcohol {
	private String name;
	private String type;
	private float rate;
	private float alcoholLevel;
	private String imageUrl;
	private int taste;
	private int flavor;
	private int corps;
	
	public Alcohol() {
		super();
	}

	public Alcohol(String name, String imageUrl) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
	}

	public Alcohol(String name, String imageUrl, int taste, int flavor, int corps) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.taste = taste;
		this.flavor = flavor;
		this.corps = corps;
	}

	public Alcohol(String name, String type, float rate, float alcoholLevel, String imageUrl, int taste, int flavor,
			int corps) {
		super();
		this.name = name;
		this.type = type;
		this.rate = rate;
		this.alcoholLevel = alcoholLevel;
		this.imageUrl = imageUrl;
		this.taste = taste;
		this.flavor = flavor;
		this.corps = corps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getAlcoholLevel() {
		return alcoholLevel;
	}

	public void setAlcoholLevel(float alcoholLevel) {
		this.alcoholLevel = alcoholLevel;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public int getFlavor() {
		return flavor;
	}

	public void setFlavor(int flavor) {
		this.flavor = flavor;
	}

	public int getCorps() {
		return corps;
	}

	public void setCorps(int corps) {
		this.corps = corps;
	}

	@Override
	public String toString() {
		return "name=" + name + ", type=" + type + ", rate=" + rate + ", alcoholLevel=" + alcoholLevel
				+ ", imageUrl=" + imageUrl + ", taste=" + taste + ", flavor=" + flavor + ", corps=" + corps;
	}
}
