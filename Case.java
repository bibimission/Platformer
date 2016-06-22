public class Case{

	private int posX;
	private int posY;
	public boolean isFranchissable;
	
	public boolean isPlatform =false;
	public boolean isBouton =false;
	public boolean faitBobo =false;
	
	//type:  0= Simple, 1=Obstacle, 2=Plateforme, 3=Bouton
	 public int type;
	
	public Case(int posX, int posY,int type){
		this.posX = posX;
		this.posY = posY;
		this.type =type;
		if(this.type==0){
			this.isFranchissable=true;
		}
		if(this.type==1){
			this.isFranchissable=false;
		}
		if(this.type==2){
			this.isFranchissable=true;
			this.isPlatform =true;
		}
		if(this.type==3){
			this.isFranchissable=true;
			this.isBouton =true;
		}
		if(this.type==4){
			this.isFranchissable=true;
			this.faitBobo =true;
		}
	}
	
	public int getX(){
		int ret = this.posX;
		return ret;
	}
	
	public int getY(){
		int ret = this.posY;
		return ret;
	}
	
	public int getType(){
		int ret = this.type;
		return ret;
	}
	
	public void setType(int t){
		if(t<5 && t>=0){
			this.type=t;
		}
	}
	
	public void dimoitou(){
		System.out.println("X:"+this.posX+" Y:"+this.posY);
	}
}