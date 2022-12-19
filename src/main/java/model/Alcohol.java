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
	
	final private String[] tasteHashTag = new String[] {"맛", "감칠맛", "단맛", "신맛", "쓴맛", "짠맛"};
	final private String[] flavorHashTag = new String[] {"향", "국화", "매화", "아카시아", "연꽃", "장미",
			"감귤", "딸기", "매실", "멜론", "바나나", "배", "복숭아", "사과", "산딸기", "살구", "유자", "자두", "참외", "파인애플",
			"감자", "갓 지은 밥", "고구마", "곡물가루", "누룽지", "밀", "생쌀", "옥수수",
			"땅콩", "밤", "아몬드", "잣",
			"감초", "계피", "구기자", "대추", "더덕/도라지", "산수유나무 열매", "생간", "송순", "쑥", "오가피", "오미자", "인삼", "황기",
			"나무", "나무껍질", "대나무잎", "무", "버섯", "봉숭아", "솔잎", "연잎", "지푸라기", "풀",
			"꿀", "엿기름", "조청", "카라멜",
			"바닐라", "박하", "청향", "후추",
			"버터", "요거트", "우유", "치즈",
			"간장", "누룩향", "메주", "숙성향", "효모",
			"가열취", "고무", "곰팡이", "기름냄새", "누룩취", "먼지", "삶은 채소", "상한 부추", "상한 양파", "상한 우유",
			"쉰내", "식초", "썩은내", "유황", "이끼", "종이", "지린내", "쉰내", "흙"};
	final private String[] corpsHashTag = new String[] {"바디감", "군칠감", "떫은맛", "목넘김", "무게감",
			"부드러움", "시원함", "여운", "자극적인 맛", "톡쏘는 맛"};
	private String tasteString;
	private String flavorString;
	private String corpsString;
	
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
		if (taste != 0) {
			this.tasteString = tasteHashTag[taste]; }
		if (flavor != 0) {
			this.flavorString = flavorHashTag[flavor]; }
		if (corps != 0) {
			this.corpsString = corpsHashTag[corps]; }
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
		if (taste != 0) {
			this.tasteString = tasteHashTag[taste]; }
		if (flavor != 0) {
			this.flavorString = flavorHashTag[flavor]; }
		if (corps != 0) {
			this.corpsString = corpsHashTag[corps]; }
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
		if (taste != 0) {
			this.tasteString = tasteHashTag[taste]; }
		if (flavor != 0) {
			this.flavorString = flavorHashTag[flavor]; }
		if (corps != 0) {
			this.corpsString = corpsHashTag[corps]; }
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
		if (taste != 0) {
			this.tasteString = tasteHashTag[taste]; }
	}

	public int getFlavor() {
		return flavor;
	}

	public void setFlavor(int flavor) {
		this.flavor = flavor;
		if (flavor != 0) {
			this.flavorString = flavorHashTag[flavor]; }
	}

	public int getCorps() {
		return corps;
	}

	public void setCorps(int corps) {
		this.corps = corps;
		if (corps != 0) {
			this.corpsString = corpsHashTag[corps]; }
	}
	
	public String[] getTasteHashTag() {
		return new String[] {"감칠맛", "단맛", "신맛", "쓴맛", "짠맛"};
	}

	public String[] getFlavorHashTag() {
		return new String[] {"국화", "매화", "아카시아", "연꽃", "장미",
				"감귤", "딸기", "매실", "멜론", "바나나", "배", "복숭아", "사과", "산딸기", "살구", "유자", "자두", "참외", "파인애플",
				"감자", "갓 지은 밥", "고구마", "곡물가루", "누룽지", "밀", "생쌀", "옥수수",
				"땅콩", "밤", "아몬드", "잣",
				"감초", "계피", "구기자", "대추", "더덕/도라지", "산수유나무 열매", "생간", "송순", "쑥", "오가피", "오미자", "인삼", "황기",
				"나무", "나무껍질", "대나무잎", "무", "버섯", "봉숭아", "솔잎", "연잎", "지푸라기", "풀",
				"꿀", "엿기름", "조청", "카라멜",
				"바닐라", "박하", "청향", "후추",
				"버터", "요거트", "우유", "치즈",
				"간장", "누룩향", "메주", "숙성향", "효모",
				"가열취", "고무", "곰팡이", "기름냄새", "누룩취", "먼지", "삶은 채소", "상한 부추", "상한 양파", "상한 우유",
				"쉰내", "식초", "썩은내", "유황", "이끼", "종이", "지린내", "쉰내", "흙"};
	}

	public String[] getCorpsHashTag() {
		return new String[] {"군칠감", "떫은맛", "목넘김", "무게감",
				"부드러움", "시원함", "여운", "자극적인 맛", "톡쏘는 맛"};
	}

	public String getTasteString() {
		return tasteString;
	}

	public void setTasteString(String taste) {
		tasteString = taste;
		int i = -1;
		for (i = 1; i < tasteHashTag.length; i++) {
			if (tasteString.equals(tasteHashTag[i]))
				break;
		}
		this.taste = i;
	}

	public String getFlavorString() {
		return flavorString;
	}

	public void setFlavorString(String flavor) {
		flavorString = flavor;
		int i = -1;
		for (i = 1; i < flavorHashTag.length; i++) {
			if (flavorString.equals(flavorHashTag[i]))
				break;
		}
		this.flavor = i;
	}

	public String getCorpsString() {
		return corpsString;
	}

	public void setCorpsString(String corps) {
		corpsString = corps;
		int i = -1;
		for (i = 1; i < corpsHashTag.length; i++) {
			if (corpsString.equals(corpsHashTag[i]))
				break;
		}
		this.corps = i;
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
