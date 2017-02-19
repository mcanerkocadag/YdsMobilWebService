package models;

public class ExamModel2 {
	
	private int idexam;
	private String name;
	private int kelimeBilgisi;
	private int dilBilgisi;
	private int clozeTest;
	private int cumleTam;
	private int ingTur;
	private int turIng;
	private int okumaParca;
	private int diyalogTam;
	private int enYakin;
	private int paragrafTam;
	private int anlamBozan;
	private String status;
	
	public int getIdexam() {
		return idexam;
	}



	public void setIdexam(int idexam) {
		this.idexam = idexam;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getKelimeBilgisi() {
		return kelimeBilgisi;
	}



	public void setKelimeBilgisi(int kelimeBilgisi) {
		this.kelimeBilgisi = kelimeBilgisi;
	}



	public int getDilBilgisi() {
		return dilBilgisi;
	}



	public void setDilBilgisi(int dilBilgisi) {
		this.dilBilgisi = dilBilgisi;
	}



	public int getClozeTest() {
		return clozeTest;
	}



	public void setClozeTest(int clozeTest) {
		this.clozeTest = clozeTest;
	}



	public int getCumleTam() {
		return cumleTam;
	}



	public void setCumleTam(int cumleTam) {
		this.cumleTam = cumleTam;
	}



	public int getIngTur() {
		return ingTur;
	}



	public void setIngTur(int ingTur) {
		this.ingTur = ingTur;
	}



	public int getTurIng() {
		return turIng;
	}



	public void setTurIng(int turIng) {
		this.turIng = turIng;
	}



	public int getOkumaParca() {
		return okumaParca;
	}



	public void setOkumaParca(int okumaParca) {
		this.okumaParca = okumaParca;
	}



	public int getDiyalogTam() {
		return diyalogTam;
	}



	public void setDiyalogTam(int diyalogTam) {
		this.diyalogTam = diyalogTam;
	}



	public int getEnYakin() {
		return enYakin;
	}



	public void setEnYakin(int enYakin) {
		this.enYakin = enYakin;
	}



	public int getParagrafTam() {
		return paragrafTam;
	}



	public void setParagrafTam(int paragrafTam) {
		this.paragrafTam = paragrafTam;
	}



	public int getAnlamBozan() {
		return anlamBozan;
	}



	public void setAnlamBozan(int anlamBozan) {
		this.anlamBozan = anlamBozan;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getJsonString(){
		String json = null;
		json ="{\"idexam\":\"" + this.idexam + "\","
				+"\"name\":\"" + this.name + "\","
				+"\"kelimeBilgisi\":\"" + this.kelimeBilgisi + "\","
				+"\"dilBilgisi\":\"" + this.dilBilgisi + "\","
				+"\"clozeTest\":\"" + this.clozeTest + "\","
				+"\"cumleTam\":\"" + this.cumleTam + "\","
				+"\"ingTur\":\"" + this.ingTur + "\","
				+"\"turIng\":\"" + this.turIng + "\","
				+"\"okumaParca\":\"" + this.okumaParca + "\","
				+"\"diyalogTam\":\"" + this.diyalogTam + "\","
				+"\"enYakin\":\"" + this.enYakin + "\","
				+"\"paragrafTam\":\"" + this.paragrafTam + "\","
				+"\"anlamBozan\":\"" + this.anlamBozan + "\","
				+"\"status\":\"" + this.status + "\"}";
		
		return json;
	}
	
}
