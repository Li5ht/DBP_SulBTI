package model;

public class Alcohol {
	private long alcoholId;
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
	
	public Alcohol(long alcoholId, String name, String imageUrl) {
		super();
		this.alcoholId = alcoholId;
		this.name = name;
		this.imageUrl = imageUrl;
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
	
	public Alcohol(long alcoholId, String name, String type, float rate, float alcoholLevel, String imageUrl, int taste,
			int flavor, int corps) {
		super();
		this.alcoholId = alcoholId;
		this.name = name;
		this.type = type;
		this.rate = rate;
		this.alcoholLevel = alcoholLevel;
		this.imageUrl = imageUrl;
		this.taste = taste;
		this.flavor = flavor;
		this.corps = corps;
	}

	public void setAlcoholId(long alcoholId) {
		this.alcoholId = alcoholId;
	}
	
	public long getAlcoholId() {
		return alcoholId;
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
	
	public String toCalendarString() {
		return name + " ";
	}
	
	public float calRate(int flag, float rate, float oldRate, int num, float newRate) {
		if (flag == 0) {	// 리뷰 추가
			return (rate * num + newRate) / (num+1);
		} else if (flag == 1) {	// 리뷰 수정
			return (rate * num - oldRate + newRate) / num;
		} else {	// 리뷰 삭제
			if (num -1 == 0)
				return 0;
			else 
				return (rate * num - oldRate) / (num-1);
		}
		
	}
	
	public int calTaste(int flag, int[] num, int oldTaste, int newTaste) {
		if (flag == 0) {
			num[newTaste]++;
		} else if (flag == 1) {
			num[oldTaste]--;
			num[newTaste]++;
		} else {
			num[oldTaste]--;
		}
		
		
		int max = num[0];
		int maxIndex = 0;
		for (int i = 0; i < num.length; i++) {
			if (max < num[i]) {
				max = num[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public int calFlavor(int flag, int[] num, int oldFlavor, int newFlavor) {
		if (flag == 0) {
			num[newFlavor]++;
		} else if (flag == 1) {
			num[oldFlavor]--;
			num[newFlavor]++;
		} else {
			num[oldFlavor]--;
		}
		
		int max = num[0];
		int maxIndex = 0;
		for (int i = 0; i < num.length; i++) {
			if (max < num[i]) {
				max = num[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	public int calCorps(int flag, int[] num, int oldCorps, int newCorps) {
		if (flag == 0) {
			num[newCorps]++;
		} else if (flag == 1) {
			num[oldCorps]--;
			num[newCorps]++;
		} else {
			num[oldCorps]--;
		}
		
		int max = num[0];
		int maxIndex = 0;
		for (int i = 0; i < num.length; i++) {
			if (max < num[i]) {
				max = num[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
}
