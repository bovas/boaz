package com.mycare.actions.utils.scjp.design;

class Something {		
        private Something() {
        }
 
        private static class LazyHolder {
                private static final Something INSTANCE = new Something();
        }
 
        public static Something getInstance() {
                return LazyHolder.INSTANCE;
        }
}
public class Singletom{
	public static void main(String[] args) {
		Something some = Something.getInstance();
		Something some1 = Something.getInstance();
		System.out.println(some == some1);
	}
}


class SomethingOld{
	public static SomethingOld instance;
	private SomethingOld() {
		
	}
	public static SomethingOld getInstance(){
		
		synchronized(SomethingOld.class){
			if(instance !=null)
				instance = new SomethingOld();
		}
		
		return instance;		
	}
}
