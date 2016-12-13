package valueObjecte;

public class Schiff {

	private int[] schiff_id;
	private String[] schiff_name;
	private int[] freiePlaetze;
	private int[] belegtePlaetze;
	
	
	public Schiff() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int[] getSchiff_id() {
		return schiff_id;
	}
	public void setSchiff_id(int[] schiff_id) {
		this.schiff_id = schiff_id;
	}
	public String[] getSchiff_name() {
		return schiff_name;
	}
	public void setSchiff_name(String[] schiff_name) {
		this.schiff_name = schiff_name;
	}
	public int[] getFreiePlaetze() {
		return freiePlaetze;
	}
	public void setFreiePlaetze(int[] freiePlaetze) {
		this.freiePlaetze = freiePlaetze;
	}
	public int[] getBelegtePlaetze() {
		return belegtePlaetze;
	}
	public void setBelegtePlaetze(int[] belegtePlaetze) {
		this.belegtePlaetze = belegtePlaetze;
	}

}
